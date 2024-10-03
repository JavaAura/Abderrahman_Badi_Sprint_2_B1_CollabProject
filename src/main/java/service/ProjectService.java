package service;

import model.Project;
import repository.implementation.ProjectRepositoryImpl;
import repository.interfaces.ProjectRepository;

import java.util.List;

public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService() {
        this.projectRepository = new ProjectRepositoryImpl();
    }


    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

}
