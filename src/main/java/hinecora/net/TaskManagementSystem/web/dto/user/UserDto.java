package hinecora.net.TaskManagementSystem.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import hinecora.net.TaskManagementSystem.web.dto.validation.OnCreate;
import hinecora.net.TaskManagementSystem.web.dto.validation.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "UserDTO")
public class UserDto {

    @Schema(description = "User id", example = "1")
    @NotNull(message = "Id must be not null.", groups = {OnUpdate.class})
    private Long id;

    @Schema(description = "User email", example = "Ivan@mail.ru")
    @Email
    @NotNull(message = "Email must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Username length must be smaller than 255 symbols.", groups = {OnUpdate.class, OnCreate.class})
    private String email;

    @Schema(description = "User crypted password", example = "$2a$10$E0Yz21hNwz/wIuzv8CCT2ezTsAQo67.TWBJitsPWhgwpzHJvNcWrG")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null.", groups = {OnUpdate.class, OnCreate.class})
    private String password;

    @Schema(description = "User password confirmation", example = "$2a$10$E0Yz21hNwz/wIuzv8CCT2ezTsAQo67.TWBJitsPWhgwpzHJvNcWrG")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation must be not null.", groups = {OnCreate.class})
    private String passwordConfirmation;

}
