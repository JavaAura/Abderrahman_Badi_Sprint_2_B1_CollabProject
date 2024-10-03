package controller;

import model.enums.ProjectStatus;
import service.ProjectService;
import model.Project;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class ProjectServlet extends HttpServlet {

    private ProjectService projectService = new ProjectService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchQuery = request.getParameter("search");

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            request.setAttribute("message", "Résultats de la recherche.");
            List<Project> projects = projectService.searchProjects(searchQuery);

            System.out.println("Résultats de la recherche pour : " + searchQuery);
            projects.forEach(project -> System.out.println(project.getName()));

            request.setAttribute("projects", projects);
            request.getRequestDispatcher("views/projects.jsp").forward(request, response);
        }

        List<Project> projects = projectService.getAllProjects();

        projects.forEach(project -> System.out.println(project.getName()));
        request.setAttribute("projects", projects);

        request.getRequestDispatcher("views/projects.jsp").forward(request, response);
    }





    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        if ("update".equals(action)) {
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

            projectService.updateProject(project);

            request.setAttribute("message", "Projet mis à jour avec succès !");

            List<Project> projects = projectService.getAllProjects();
            request.setAttribute("projects", projects);

            request.getRequestDispatcher("views/projects.jsp").forward(request, response);

        } else if (action != null && action.equals("delete")) {
            try {
                int id = Integer.parseInt(idParam);
                projectService.deleteProject(id);

                request.setAttribute("message", "Projet supprimé avec succès.");
                List<Project> projects = projectService.getAllProjects();
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("views/projects.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "ID de projet invalide.");
                request.getRequestDispatcher("views/projects.jsp").forward(request, response);
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


            projectService.addProject(newProject);

            request.setAttribute("message", "Projet ajouté avec succès !");
            List<Project> projects = projectService.getAllProjects();
            request.setAttribute("projects", projects);

            request.getRequestDispatcher("views/projects.jsp").forward(request, response);
        }
    }





}
