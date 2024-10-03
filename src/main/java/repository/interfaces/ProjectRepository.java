package repository.interfaces;

import model.Project;

import java.util.List;

public interface ProjectRepository {
    public List<Project> getAllProjects();
    public void updateProject(Project project);
    void deleteProject(int id);
    void addProject(Project project);
    List<Project> searchProjectsByName(String name);
    public List<Project> getAllProjectsPaginated(int limit, int offset);
    public int getTotalProjectsCount();

}
