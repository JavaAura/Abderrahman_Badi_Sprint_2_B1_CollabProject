package repository.interfaces;

import model.Project;

import java.util.List;

public interface ProjectRepository {
    public List<Project> getAllProjects();
    public void updateProject(Project project);
}
