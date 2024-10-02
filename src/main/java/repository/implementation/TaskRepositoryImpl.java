package repository.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import config.DatabaseConnection;
import model.Project;
import model.Task;
import model.enums.TaskPriority;
import model.enums.TaskStatus;
import repository.interfaces.TaskRepository;

public class TaskRepositoryImpl implements TaskRepository {

    private static final Logger logger = LoggerFactory.getLogger(TaskRepositoryImpl.class);

    private static final String SQL_FIND_BY_ID = "SELECT * FROM task WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM task WHERE project_id = ?";
    private static final String SQL_INSERT = "INSERT INTO task (`title`, `description`, `priority`, `task_statut`, `project_id`) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE task SET title = ?, description = ?, priority = ?, task_statut = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM task WHERE id = ?";

    @Override
    public List<Task> getAllTasks(Project project) {
        List<Task> tasks = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_LIST);) {
            ps.setLong(1, project.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task.setId(rs.getLong("id"));
                    task.setTitle(rs.getString("title"));
                    task.setDescription(rs.getString("description"));
                    task.setTaskPriority(TaskPriority.valueOf(rs.getString("priority")));
                    task.setTaskStatus(TaskStatus.valueOf(rs.getString("task_statut")));
                    task.setProject(project);

                    tasks.add(task);
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public Optional<Task> get(long id) {
        Project project = new Project();
        Task task = null;
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID);) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    TaskPriority taskPriority = TaskPriority.valueOf(rs.getString("priority"));
                    TaskStatus taskStatus = TaskStatus.valueOf(rs.getString("task_statut"));
                    // project.setId(rs.getLong("project_id"));

                    task = new Task(id, title, description, taskPriority, taskStatus, project);

                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return task != null ? Optional.of(task) : Optional.empty();

    }

    @Override
    public void save(Task task) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getTaskPriority().toString());
            ps.setString(4, task.getTaskStatus().toString());
            ps.setLong(5, task.getProject().getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error saving task: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(long id, Task updatedTask) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, updatedTask.getTitle());
            ps.setString(2, updatedTask.getDescription());
            ps.setString(3, updatedTask.getTaskPriority().toString());
            ps.setString(4, updatedTask.getTaskStatus().toString());
            ps.setLong(5, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                logger.info("Task with ID " + id + " was successfully updated.");
            } else {
                logger.warn("No task found with ID " + id);
            }

        } catch (SQLException e) {
            logger.error("Error updating task: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Task task) {
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {

            ps.setLong(1, task.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting task: " + e.getMessage(), e);
        }

    }

}
