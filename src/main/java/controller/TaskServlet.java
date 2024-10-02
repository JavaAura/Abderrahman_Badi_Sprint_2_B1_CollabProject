package controller;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaskServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(TaskServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("accessed tasks page");
        logger.warn("accessed tasks page");
    
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/tasks.jsp");
        dispatcher.forward(req, resp);
    }

}
