package hinecora.net.TaskManagementSystem.web.controller;

import hinecora.net.TaskManagementSystem.domain.user.User;
import hinecora.net.TaskManagementSystem.service.AuthService;
import hinecora.net.TaskManagementSystem.service.UserService;
import hinecora.net.TaskManagementSystem.web.dto.auth.JwtRequest;
import hinecora.net.TaskManagementSystem.web.dto.auth.JwtResponse;
import hinecora.net.TaskManagementSystem.web.dto.user.UserDto;
import hinecora.net.TaskManagementSystem.web.dto.validation.OnCreate;
import hinecora.net.TaskManagementSystem.web.mappers.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth controller", description = "Auth API")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(
            @Validated @RequestBody JwtRequest loginRequest
    ) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(
            @Validated(OnCreate.class)
            @RequestBody UserDto userDto
    ) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(
            @RequestBody String refreshToken
    ) {
        return authService.refresh(refreshToken);
    }

}
