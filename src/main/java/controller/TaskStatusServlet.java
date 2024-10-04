package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.enums.TaskStatus;
import service.TaskService;

public class TaskStatusServlet extends HttpServlet {

    private TaskService taskService = new TaskService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long taskId = Long.parseLong(req.getParameter("taskId"));
        TaskStatus status = TaskStatus.valueOf(req.getParameter("status"));

        try {
            taskService.updateTaskStatus(taskId, status);
        } catch (Exception e) {
            
        }


    }
}
