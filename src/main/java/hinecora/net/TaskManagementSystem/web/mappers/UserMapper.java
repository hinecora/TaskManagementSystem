package hinecora.net.TaskManagementSystem.web.mappers;

import hinecora.net.TaskManagementSystem.domain.user.User;
import hinecora.net.TaskManagementSystem.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}
