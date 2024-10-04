package repository.interfaces;

import java.util.List;
import java.util.Optional;

import model.Project;
import model.Task;
import model.enums.TaskStatus;

public interface TaskRepository {

    public Optional<Task> get(long id);

    public List<Task> getAllTasks(Project project);

    public void assignMemberToTask(long task_id, long member_id);

    public void save(Task task);

    public void save(Task task, long member_id);

    public void update(long id, Task task);

    public void updateTaskStatus(long id, TaskStatus taskStatus);

    public void delete(Task task);

}
