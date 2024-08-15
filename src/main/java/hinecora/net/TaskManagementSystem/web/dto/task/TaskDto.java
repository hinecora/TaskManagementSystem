package hinecora.net.TaskManagementSystem.web.dto.task;

import hinecora.net.TaskManagementSystem.domain.task.Priority;
import hinecora.net.TaskManagementSystem.domain.task.Status;
import hinecora.net.TaskManagementSystem.web.dto.validation.OnCreate;
import hinecora.net.TaskManagementSystem.web.dto.validation.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "TaskDTO")
public class TaskDto {

    @Schema(description = "Task id", example = "1")
    @NotNull(message = "Id must be not null.", groups = {OnUpdate.class})
    private long id;

    @Schema(description = "Task title", example = "Call somebody")
    @NotNull(message = "Title must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Title length must be smaller than 255 symbols.", groups = {OnUpdate.class, OnCreate.class})
    private String title;

    @Schema(description = "Task description", example = "Do something")
    @Length(max = 255, message = "Description length must be smaller than 255 symbols.", groups = {OnUpdate.class, OnCreate.class})
    private String description;

    @Schema(description = "Task status", example = "TODO")
    private Status status;

    @Schema(description = "Task priority", example = "LOW")
    private Priority priority;

    @Schema(description = "Task owner", example = "Ivan@mail.com")
    private String owner;

    @Schema(description = "Task executor", example = "John@mail.com")
    @NotNull(message = "Field executor must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Field executor length must be smaller than 255 symbols.", groups = {OnUpdate.class, OnCreate.class})
    private String executor;

}
