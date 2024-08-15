package hinecora.net.TaskManagementSystem.service.impl;

import hinecora.net.TaskManagementSystem.domain.exception.ResourceNotFoundException;
import hinecora.net.TaskManagementSystem.domain.task.Status;
import hinecora.net.TaskManagementSystem.domain.task.Task;
import hinecora.net.TaskManagementSystem.domain.user.User;
import hinecora.net.TaskManagementSystem.repository.TaskRepository;
import hinecora.net.TaskManagementSystem.service.TaskService;
import hinecora.net.TaskManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;


    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "TaskService::getById", key = "#id")
    public Task getById(
            Long id
    ) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(
            Long id
    ) {
        return taskRepository.findAllByUserId(id);
    }

    @Override
    @Transactional
    @CachePut(value = "TaskService::getById", key = "#task.id")
    public Task update(
            Task task
    ) {
        if (task.getStatus() == null) {
            task.setStatus(Status.TODO);
        }
        taskRepository.save(task);
        return task;
    }

    @Override
    @Transactional
    @CachePut(value = "TaskService::getById", key = "#task.id")
    public Task updateStatus(
            Long userId,
            Task task,
            Status status
    ) {
        if (userService.getById(userId).getEmail().equals(task.getExecutor())) {
            task.setStatus(status);
            taskRepository.save(task);
            return task;
        } else {
            throw new ResourceNotFoundException("You are not a executor for this task.");
        }
    }

    @Override
    @Transactional
    public Task create(
            Task task,
            Long userId
    ) {
        if (task.getStatus() == null) {
            task.setStatus(Status.TODO);
        }
//        User user = userService.getById(userId);
//        user.getTasks().add(task);
//        task.setOwner(user.getEmail());
//        if (userService.getByEmail(task.getExecutor()) == null) {
//            task.setExecutor("");
//        }
//        taskRepository.save(task);
//        userService.update(user);
        taskRepository.save(task);
        taskRepository.assignTask(userId, task.getId());
        return task;
    }

    @Override
    @Transactional
    @CacheEvict(value = "TaskService::getById", key = "#id")
    public void delete(
            Long id
    ) {
        taskRepository.deleteById(id);
    }

}
