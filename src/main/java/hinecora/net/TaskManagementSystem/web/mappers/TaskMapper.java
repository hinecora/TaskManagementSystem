package hinecora.net.TaskManagementSystem.web.mappers;

import hinecora.net.TaskManagementSystem.domain.task.Task;
import hinecora.net.TaskManagementSystem.web.dto.task.TaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDto> {
}