package hinecora.net.TaskManagementSystem.repository;

import hinecora.net.TaskManagementSystem.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = """
            SELECT * FROM comments c
            JOIN tasks_comments tc ON tc.comment_id = c.id
            WHERE tc.task_id = :taskId
            """, nativeQuery = true)
    List<Comment> findAllByTaskId(
            @Param("taskId") Long taskId
    );
}
