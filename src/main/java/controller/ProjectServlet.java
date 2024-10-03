package controller;

import model.enums.ProjectStatus;
import service.ProjectService;
import model.Project;
import util.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ProjectServlet extends HttpServlet {

    private ProjectService projectService = new ProjectService();
    private Validator validator = new Validator();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchQuery = request.getParameter("search");
        System.out.println("Search Query: " + searchQuery);
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            List<Project> projects = projectService.searchProjects(searchQuery);

            if (projects.isEmpty()) {
                request.setAttribute("message", "Project does not exist!");
                System.out.println("Aucun projet trouvé pour la requête: " + searchQuery);
            } else {
                request.setAttribute("message", "Search results for: " + searchQuery);
                projects.forEach(project -> System.out.println(project.getName()));
            }

            request.setAttribute("projects", projects);
            request.getRequestDispatcher("views/projects.jsp").forward(request, response);
            return;
        }


        List<Project> projects = projectService.getAllProjects();
        request.setAttribute("projects", projects);
        request.getRequestDispatcher("views/projects.jsp").forward(request, response);
    }


    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");
        List<String> errors = new ArrayList<>(); // List to store validation errors

        if (action != null && action.equals("update")) {
            int id = Integer.parseInt(idParam);
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
            LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
            String statut = request.getParameter("statut");

            Project project = new Project();
            project.setId(id);
            project.setName(nom);
            project.setDescription(description);
            project.setStartDate(dateDebut);
            project.setEndDate(dateFin);
            project.setStatus(ProjectStatus.valueOf(statut));

            // Validate the project
            errors = validator.validateProject(project); // Use the Validator

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("project", project);
                List<Project> projects = projectService.getAllProjects();
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("views/projects.jsp").forward(request, response);
                return; // Stop further processing if there are errors
            }

            projectService.updateProject(project);
            request.setAttribute("message", "Projet mis à jour avec succès !");
        } else if (action != null && action.equals("delete")) {
            try {
                int id = Integer.parseInt(idParam);
                projectService.deleteProject(id);
                request.setAttribute("message", "Projet supprimé avec succès.");
            } catch (NumberFormatException e) {
                request.setAttribute("message", "ID de projet invalide.");
            }
        } else if (action != null && action.equals("add")) {
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
            LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
            String statut = request.getParameter("statut");

            Project newProject = new Project();
            newProject.setName(nom);
            newProject.setDescription(description);
            newProject.setStartDate(dateDebut);
            newProject.setEndDate(dateFin);
            newProject.setStatus(ProjectStatus.valueOf(statut));

            // Validate the new project
            errors = validator.validateProject(newProject); // Use the Validator

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("project", newProject);
                List<Project> projects = projectService.getAllProjects();
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("views/projects.jsp").forward(request, response);
                return; // Stop further processing if there are errors
            }

            projectService.addProject(newProject);
            request.setAttribute("message", "Projet ajouté avec succès !");
        }

        // Always retrieve the list of projects at the end to display them
        List<Project> projects = projectService.getAllProjects();
        request.setAttribute("projects", projects);
        request.getRequestDispatcher("views/projects.jsp").forward(request, response);
    }






}
