package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.LoggerFactory;

import model.Project;
import model.Task;
import model.enums.ProjectStatus;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String projectIdParam = req.getParameter("project_id");
        long projectId = -1;

        // To be deleted
        Project project = new Project("Project Test",
                "Lorem ipsum dolor sit amet. Phasellus iaculis eros ipsum, ut facilisis lacus malesuada sit amet. Fusce luctus congue vehicula. ",
                LocalDate.now(), LocalDate.of(2024, 12, 10), ProjectStatus.IN_PROGRESS, null);

        // Parse the String to long type if its not empty/null
        if (projectIdParam != null && !projectIdParam.trim().isEmpty())
            projectId = Long.parseLong(projectIdParam);

        if (projectId != -1) {
            // Project project = projectService.get(projectId);

            // To be deleted
            project.setId(6);

            List<Task> tasks = taskService.getAllTasks(project);
            req.setAttribute("project", project);
            req.setAttribute("tasks", tasks);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("views/tasks.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }

}
