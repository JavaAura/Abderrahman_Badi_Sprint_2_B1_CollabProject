package controller;

import service.ProjectService;
import model.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/projects")
public class ProjectServlet extends HttpServlet {

    private ProjectService projectService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer tous les projets via le service
        List<Project> projects = projectService.getAllProjects();

        // Ajouter les projets à l'attribut de la requête
        request.setAttribute("projects", projects);

        // Envoyer la requête à la page JSP pour afficher les projets
        request.getRequestDispatcher("/projects.jsp").forward(request, response);
    }
}
