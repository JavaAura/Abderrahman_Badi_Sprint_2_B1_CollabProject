package repository.interfaces;

import java.util.List;
import java.util.Optional;

import model.Project;
import model.Task;

public interface TaskRepository {

    public Optional<Task> get(long id);

    public List<Task> getAllTasks(Project project);

    void save(Task task);

    void update(long id, Task task);

    void delete(Task task);

}
