package hinecora.net.TaskManagementSystem.web.controller;

import hinecora.net.TaskManagementSystem.domain.task.Task;
import hinecora.net.TaskManagementSystem.domain.user.User;
import hinecora.net.TaskManagementSystem.service.TaskService;
import hinecora.net.TaskManagementSystem.service.UserService;
import hinecora.net.TaskManagementSystem.web.dto.task.TaskDto;
import hinecora.net.TaskManagementSystem.web.dto.user.UserDto;
import hinecora.net.TaskManagementSystem.web.dto.validation.OnCreate;
import hinecora.net.TaskManagementSystem.web.dto.validation.OnUpdate;
import hinecora.net.TaskManagementSystem.web.mappers.TaskMapper;
import hinecora.net.TaskManagementSystem.web.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @Operation(summary = "Update user")
    @PutMapping
    public UserDto update(
            @Validated(OnUpdate.class)
            @RequestBody UserDto dto
    ) {
        User user = userMapper.toEntity(dto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @Operation(summary = "Get UserDTO by id")
    @GetMapping("/{id}")
    public UserDto getById(
            @PathVariable Long id
    ) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        userService.delete(id);
    }

    @Operation(summary = "Get all users tasks")
    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksByUserId(
            @PathVariable Long id
    ) {
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }

    @Operation(summary = "Add task to user")
    @PostMapping("/{id}/createTask")
    public TaskDto createTask(
            @PathVariable Long id,
            @Validated(OnCreate.class)
            @RequestBody TaskDto dto
    ) {
        Task task = taskMapper.toEntity(dto);
        User user = userService.getById(id);
        task.setOwner(user.getEmail());
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }

    @Operation(summary = "Update task status")
    @PutMapping("/{userId}/updateStatus")
    public TaskDto updateStatus(
            @PathVariable Long userId,
            @Validated(OnUpdate.class)
            @RequestBody TaskDto dto
    ) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.updateStatus(userId, task, task.getStatus());
        return taskMapper.toDto(updatedTask);
    }

}
