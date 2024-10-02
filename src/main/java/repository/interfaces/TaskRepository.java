package repository.interfaces;

import java.util.List;
import java.util.Optional;

import model.Task;

public interface TaskRepository {

    public Optional<Task> get(long id);

    public List<Task> getAllTasks();

    void save(Task task);

    void update(Task task, String[] params);

    void delete(Task task);
    
}
