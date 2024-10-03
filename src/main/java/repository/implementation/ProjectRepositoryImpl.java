package repository.implementation;

import config.DatabaseConnection;
import repository.interfaces.ProjectRepository;
import model.Project;
import model.enums.ProjectStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectRepositoryImpl implements ProjectRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProjectRepositoryImpl.class);

    private static final String SQL_FIND_BY_ID = "SELECT * FROM Project WHERE id = ?";
    private static final String GET_ALL_PROJECTS = "SELECT * FROM Project";
    private static final String UPDATE_PROJECT = "UPDATE Project SET name = ?, description = ?, start_date = ?, end_date = ?, project_statut = ? WHERE id = ?";
    private static final String DELETE_PROJECT = "DELETE FROM Project WHERE id = ?";
    private static final String ADD_PROJECT = "INSERT INTO Project (name, description, start_date, end_date, project_statut) VALUES (?, ?, ?, ?, ?)";
    private static final String SEARCH_PROJECTS_BY_NAME = "SELECT * FROM Project WHERE name LIKE ?";

    @Override
    public Optional<Project> get(long id) {
        Project project = null;

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID);) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    project = new Project();
                    project.setId(rs.getInt("id"));
                    project.setName(rs.getString("name"));
                    project.setDescription(rs.getString("description"));
                    project.setStartDate(rs.getDate("start_date").toLocalDate());
                    project.setEndDate(rs.getDate("end_date").toLocalDate());
                    project.setStatus(ProjectStatus.valueOf(rs.getString("project_statut")));
                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return Optional.ofNullable(project);

    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_ALL_PROJECTS);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                project.setStartDate(rs.getDate("start_date").toLocalDate());
                project.setEndDate(rs.getDate("end_date").toLocalDate());
                project.setStatus(ProjectStatus.valueOf(rs.getString("project_statut")));

                projects.add(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projects;
    }

    @Override
    public void updateProject(Project project) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE_PROJECT)) {

            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setDate(3, java.sql.Date.valueOf(project.getStartDate()));
            ps.setDate(4, java.sql.Date.valueOf(project.getEndDate()));
            ps.setString(5, project.getStatus().name());
            ps.setLong(6, project.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProject(int id) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE_PROJECT)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProject(Project project) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD_PROJECT)) {

            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setDate(3, java.sql.Date.valueOf(project.getStartDate()));
            ps.setDate(4, java.sql.Date.valueOf(project.getEndDate()));
            ps.setString(5, project.getStatus().name());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Project> searchProjectsByName(String name) {
        List<Project> projects = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(SEARCH_PROJECTS_BY_NAME)) {

            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Project project = new Project();
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                project.setStartDate(rs.getDate("start_date").toLocalDate());
                project.setEndDate(rs.getDate("end_date").toLocalDate());
                project.setStatus(ProjectStatus.valueOf(rs.getString("project_statut")));
                projects.add(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projects;
    }

}
