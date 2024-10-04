package service;

import java.util.List;
import java.util.Optional;

import model.Project;
import model.Task;
import repository.implementation.TaskRepositoryImpl;
import repository.interfaces.TaskRepository;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService() {
        this.taskRepository = new TaskRepositoryImpl();
    }

    public Optional<Task> getTaskById(long id) {
        return taskRepository.get(id);
    }

    public List<Task> getAllTasks(Project project) {
        return taskRepository.getAllTasks(project);
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(long id, Task task) {
        taskRepository.update(id, task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
