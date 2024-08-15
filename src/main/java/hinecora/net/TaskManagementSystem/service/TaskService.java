package hinecora.net.TaskManagementSystem.service;

import hinecora.net.TaskManagementSystem.domain.task.Status;
import hinecora.net.TaskManagementSystem.domain.task.Task;

import java.util.List;

public interface TaskService {

    Task getById(Long id);

    List<Task> getAllByUserId(Long id);

    Task update(Task task);

    Task updateStatus(Long userId, Task task, Status status);

    Task create(Task task, Long id);

    void delete(Long id);

}
