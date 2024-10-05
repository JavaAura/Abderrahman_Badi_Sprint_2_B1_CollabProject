package service;

import java.util.List;
import java.util.Optional;

import model.Project;
import model.Task;
import model.enums.TaskStatus;
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

    public void addTask(Task task, long member_id) {
        taskRepository.save(task, member_id);
    }

    public void updateTask(long id, Task task) {
        taskRepository.update(id, task);
    }

    public void updateTaskStatus(long id, TaskStatus taskStatus) {
        taskRepository.updateTaskStatus(id, taskStatus);
    }

    public void assignMemberToTask(long task_id, long member_id){
        taskRepository.assignMemberToTask(task_id, member_id);
    }
    
    public List<Task> getTaskByMemberId(Long memberId) {
    	return taskRepository.getTaskByMemberId(memberId);
    }

    public void deleteTask(long id) {
        taskRepository.delete(id);
    }
}
