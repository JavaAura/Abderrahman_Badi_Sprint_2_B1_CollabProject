package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;

import model.Member;
import model.Project;
import model.Task;
import model.enums.TaskPriority;
import model.enums.TaskStatus;
import service.MemberService;
import service.ProjectService;
import service.TaskService;
import util.Validator;

import org.slf4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaskServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(TaskServlet.class);
    private Validator validator = new Validator();

    private TaskService taskService = new TaskService();
    private ProjectService projectService = new ProjectService();
    private MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        index(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String idParam = req.getParameter("taskId");
        List<String> errors = new ArrayList<>();

        if (action != null && action.equals("update")) {
            int taskId = Integer.parseInt(idParam);
            String title = req.getParameter("taskTitle");
            String description = req.getParameter("taskDescription");
            TaskPriority taskPriority = TaskPriority.valueOf(req.getParameter("taskPriority"));

            Task task = new Task(title, description, taskPriority);

            errors = validator.validateTask(task);

            if (!errors.isEmpty()) {
                req.setAttribute("errors", errors);
                index(req, resp);
                return;
            }

            if (req.getParameter("squadMember") != null) {
                int memberId = Integer.parseInt(req.getParameter("squadMember"));

                taskService.assignMemberToTask(taskId, memberId);
            }

            taskService.updateTask(taskId, task);

        } else if (action != null && action.equals("delete")) {
            try {
                int id = Integer.parseInt(idParam);
                taskService.deleteTask(id);
            } catch (NumberFormatException e) {
                logger.error(e.getMessage(), e);
            }
        } else if (action != null && action.equals("add")) {

            String title = req.getParameter("taskTitle");
            String description = req.getParameter("taskDescription");
            TaskPriority taskPriority = TaskPriority.valueOf(req.getParameter("taskPriority"));
            TaskStatus taskStatus = TaskStatus.valueOf(req.getParameter("taskStatus"));

            Task task = new Task(title, description, taskPriority, taskStatus, null);

            errors = validator.validateTask(task);

            if (!errors.isEmpty()) {
                req.setAttribute("errors", errors);
                index(req, resp);
                return;
            }

            if (req.getParameter("squadMember") != null) {
                int memberId = Integer.parseInt(req.getParameter("squadMember"));

                taskService.addTask(task, memberId);
            } else
                taskService.addTask(task);
        }

        index(req, resp);

    }

    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectIdParam = req.getParameter("project_id");
        long projectId = -1;

        // Cast the String to long type if its not empty/null
        if (projectIdParam != null && !projectIdParam.trim().isEmpty())
            projectId = Long.parseLong(projectIdParam);

        if (projectId != -1) {
            Optional<Project> optionalProject = projectService.get(projectId);

            if (optionalProject.isPresent()) {
                Project project = optionalProject.get();

                if (project.getSquad() != null) {
                    List<Member> members = memberService.getMembersBySquad(project.getSquad().getId());
                    req.setAttribute("members", members);
                }

                List<Task> tasks = taskService.getAllTasks(project);
                if (!tasks.isEmpty()) {
                    req.setAttribute("todoTasks", tasks.stream().filter(task -> task.getTaskStatus() == TaskStatus.TODO)
                            .collect(Collectors.toList()));
                    req.setAttribute("doingTasks", tasks.stream()
                            .filter(task -> task.getTaskStatus() == TaskStatus.DOING).collect(Collectors.toList()));
                    req.setAttribute("doneTasks", tasks.stream().filter(task -> task.getTaskStatus() == TaskStatus.DONE)
                            .collect(Collectors.toList()));
                }

                req.setAttribute("project", project);

            } else {
                req.setAttribute("errorMessage", "Project not found.");
                logger.warn("No project found with id" + projectId);
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("../views/tasks.jsp");
        dispatcher.forward(req, resp);
    }

}
