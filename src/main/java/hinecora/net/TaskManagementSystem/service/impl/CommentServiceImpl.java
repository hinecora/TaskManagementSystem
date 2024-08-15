package hinecora.net.TaskManagementSystem.service.impl;

import hinecora.net.TaskManagementSystem.domain.comment.Comment;
import hinecora.net.TaskManagementSystem.domain.exception.ResourceNotFoundException;
import hinecora.net.TaskManagementSystem.domain.task.Task;
import hinecora.net.TaskManagementSystem.repository.CommentRepository;
import hinecora.net.TaskManagementSystem.service.CommentService;
import hinecora.net.TaskManagementSystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskService taskService;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllByTaskId(
            Long id
    ) {
        return commentRepository.findAllByTaskId(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "CommentService::getById", key = "#id")
    public Comment getById(
            Long id
    ) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found."));
    }

    @Override
    @Transactional
    public Comment create(
            Comment comment,
            Long taskId
    ) {
        Task task = taskService.getById(taskId);
        task.getComments().add(comment);
        taskService.update(task);
        return comment;
    }

}
