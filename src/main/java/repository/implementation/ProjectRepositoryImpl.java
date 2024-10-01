package repository.implementation;

import config.DatabaseConnection;
import repository.interfaces.ProjectRepository;
import model.Project;
import model.enums.ProjectStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM Project";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setNom(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                project.setDateDebut(rs.getDate("start_date").toLocalDate());
                project.setDateFin(rs.getDate("end_date").toLocalDate());


                String status = rs.getString("project_statut");
                project.setStatut(ProjectStatus.valueOf(status));

                projects.add(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projects;
    }
}
