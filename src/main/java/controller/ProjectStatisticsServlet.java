package controller;

import model.Project;
import service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProjectStatisticsServlet extends HttpServlet {

    private ProjectService projectService;

    @Override
    public void init() throws ServletException {

        projectService = new ProjectService();
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int page = 1;
        int pageSize = 8;

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        List<Object[]> projectSummaries = projectService.getProjectSummaries(page, pageSize);
        request.setAttribute("projectSummaries", projectSummaries);

        int totalProjects = projectService.getTotalProjectCount();
        int totalPages = (int) Math.ceil((double) totalProjects / pageSize);

        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("title", "Project Summary");

        request.getRequestDispatcher("/views/projectStatistics.jsp").forward(request, response);
    }



}
