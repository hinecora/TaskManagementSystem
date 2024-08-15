package hinecora.net.TaskManagementSystem.web.controller;

import hinecora.net.TaskManagementSystem.domain.comment.Comment;
import hinecora.net.TaskManagementSystem.domain.task.Task;
import hinecora.net.TaskManagementSystem.service.CommentService;
import hinecora.net.TaskManagementSystem.service.TaskService;
import hinecora.net.TaskManagementSystem.web.dto.comment.CommentDto;
import hinecora.net.TaskManagementSystem.web.dto.task.TaskDto;
import hinecora.net.TaskManagementSystem.web.dto.validation.OnCreate;
import hinecora.net.TaskManagementSystem.web.dto.validation.OnUpdate;
import hinecora.net.TaskManagementSystem.web.mappers.CommentMapper;
import hinecora.net.TaskManagementSystem.web.mappers.TaskMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
@Validated
@Tag(name = "Task Controller", description = "Task API")
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    @Operation(summary = "Update task")
    @PutMapping
    public TaskDto update(
            @Validated(OnUpdate.class)
            @RequestBody TaskDto dto
    ) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }

    @Operation(summary = "Get taskDTO by id")
    @GetMapping("/{id}")
    public TaskDto getById(
            @PathVariable Long id
    ) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @Operation(summary = "Delete task by id")
    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable Long id
    ) {
        taskService.delete(id);
    }

    @Operation(summary = "Add new comment to task")
    @PostMapping("/{id}/createComment")
    public CommentDto createComment(
            @PathVariable Long id,
            @Validated(OnCreate.class)
            @RequestBody
            CommentDto dto
    ) {
        Comment comment = commentMapper.toEntity(dto);
        Comment createdComment = commentService.create(comment, id);
        return commentMapper.toDto(createdComment);
    }

    @Operation(summary = "Get all task comments")
    @GetMapping("/{id}/comments")
    public List<CommentDto> getCommentsByTaskId(
            @PathVariable Long id
    ) {
        List<Comment> comments = commentService.getAllByTaskId(id);
        return commentMapper.toDto(comments);
    }

}
