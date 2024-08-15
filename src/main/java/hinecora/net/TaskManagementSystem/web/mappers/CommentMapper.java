package hinecora.net.TaskManagementSystem.web.mappers;

import hinecora.net.TaskManagementSystem.domain.comment.Comment;
import hinecora.net.TaskManagementSystem.web.dto.comment.CommentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends Mappable<Comment, CommentDto>{
}
