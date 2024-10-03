package repository.interfaces;

import model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    public Optional<Project> get(long id);

    public List<Project> getAllProjects();

    public void updateProject(Project project);

    public void deleteProject(int id);

    public void addProject(Project project);

    public List<Project> searchProjectsByName(String name);

}
