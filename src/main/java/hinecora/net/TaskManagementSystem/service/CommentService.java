package hinecora.net.TaskManagementSystem.service;

import hinecora.net.TaskManagementSystem.domain.comment.Comment;

import java.util.List;

public interface CommentService {

    Comment getById(Long id);

    Comment create(Comment comment, Long taskId);

    List<Comment> getAllByTaskId(Long id);
}
