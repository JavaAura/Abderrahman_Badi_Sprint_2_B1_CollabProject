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
                project.setStatut(ProjectStatus.valueOf(rs.getString("project_statut")));

                projects.add(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projects;
    }


    @Override
    public void updateProject(Project project) {
        String query = "UPDATE Project SET name = ?, description = ?, start_date = ?, end_date = ?, project_statut = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, project.getNom());
            ps.setString(2, project.getDescription());
            ps.setDate(3, java.sql.Date.valueOf(project.getDateDebut()));
            ps.setDate(4, java.sql.Date.valueOf(project.getDateFin()));
            ps.setString(5, project.getStatut().name());
            ps.setLong(6, project.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProject(int id) {
        String query = "DELETE FROM Project WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addProject(Project project) {
        String query = "INSERT INTO Project (name, description, start_date, end_date, project_statut) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, project.getNom());
            ps.setString(2, project.getDescription());
            ps.setDate(3, java.sql.Date.valueOf(project.getDateDebut()));
            ps.setDate(4, java.sql.Date.valueOf(project.getDateFin()));
            ps.setString(5, project.getStatut().name());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
