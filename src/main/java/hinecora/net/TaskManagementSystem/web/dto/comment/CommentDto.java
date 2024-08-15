package hinecora.net.TaskManagementSystem.web.dto.comment;

import hinecora.net.TaskManagementSystem.web.dto.validation.OnCreate;
import hinecora.net.TaskManagementSystem.web.dto.validation.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "CommentDTO")
public class CommentDto {

    @Schema(description = "Comment id", example = "1")
    @NotNull(message = "Id must be not null.", groups = {OnUpdate.class})
    private Long id;

    @Schema(description = "Comment author", example = "Ivan@mail.com")
    @NotNull(message = "Field executor must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Field executor length must be smaller than 255 symbols.", groups = {OnUpdate.class, OnCreate.class})
    private String author;

    @Schema(description = "Comment text", example = "this is a first comment for this task")
    @Length(max = 255, message = "Description length must be smaller than 255 symbols.", groups = {OnUpdate.class, OnCreate.class})
    private String text;



}
