package controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;

import model.Member;
import model.Project;
import model.Task;
import model.enums.TaskStatus;
import service.MemberService;
import service.ProjectService;
import service.TaskService;

import org.slf4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaskServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(TaskServlet.class);

    private TaskService taskService = new TaskService();
    private ProjectService projectService = new ProjectService();
    private MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String projectIdParam = req.getParameter("project_id");
        long projectId = -1;

        // Parse the String to long type if its not empty/null
        if (projectIdParam != null && !projectIdParam.trim().isEmpty())
            projectId = Long.parseLong(projectIdParam);

        if (projectId != -1) {
            Optional<Project> optionalProject = projectService.get(projectId);

            if (optionalProject.isPresent()) {
                Project project = optionalProject.get();
                List<Task> tasks = taskService.getAllTasks(project);
                List<Member> members = memberService.getMembersBySquad(project.getSquad().getId());

                if (!tasks.isEmpty()) {
                    req.setAttribute("todoTasks", tasks.stream().filter(task -> task.getTaskStatus() == TaskStatus.TODO)
                            .collect(Collectors.toList()));
                    req.setAttribute("doingTasks", tasks.stream()
                            .filter(task -> task.getTaskStatus() == TaskStatus.DOING).collect(Collectors.toList()));
                    req.setAttribute("doneTasks", tasks.stream().filter(task -> task.getTaskStatus() == TaskStatus.DONE)
                            .collect(Collectors.toList()));
                }

                req.setAttribute("project", project);
                req.setAttribute("members", members);

            } else {
                req.setAttribute("errorMessage", "Project not found.");
                logger.warn("No project found with id" + projectId);
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("../views/tasks.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }

}
