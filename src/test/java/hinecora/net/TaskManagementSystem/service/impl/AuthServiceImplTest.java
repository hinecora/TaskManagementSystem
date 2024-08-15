package hinecora.net.TaskManagementSystem.service.impl;

import hinecora.net.TaskManagementSystem.config.TestConfig;
import hinecora.net.TaskManagementSystem.domain.exception.ResourceNotFoundException;
import hinecora.net.TaskManagementSystem.domain.user.Role;
import hinecora.net.TaskManagementSystem.domain.user.User;
import hinecora.net.TaskManagementSystem.repository.TaskRepository;
import hinecora.net.TaskManagementSystem.repository.UserRepository;
import hinecora.net.TaskManagementSystem.web.dto.auth.JwtRequest;
import hinecora.net.TaskManagementSystem.web.dto.auth.JwtResponse;
import hinecora.net.TaskManagementSystem.web.security.JwtTokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthServiceImpl authService;

    @Test
    void login() {
        Long userId = 1L;
        String email = "user@mail.com";
        String password = "password";
        Set<Role> roles = Collections.emptySet();
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";
        JwtRequest request = new JwtRequest();
        request.setEmail(email);
        request.setPassword(password);
        User user = new User();
        user.setId(userId);
        user.setEmail(email);
        user.setRoles(roles);
        Mockito.when(userService.getByEmail(email))
                .thenReturn(user);
        Mockito.when(tokenProvider.createAccessToken(userId, email, roles))
                .thenReturn(accessToken);
        Mockito.when(tokenProvider.createRefreshToken(userId, email))
                .thenReturn(refreshToken);
        JwtResponse response = authService.login(request);
        Mockito.verify(authenticationManager)
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword())
                );
        Assertions.assertEquals(response.getEmail(), email);
        Assertions.assertEquals(response.getId(), userId);
    }

    @Test
    void loginWithIncorrectUsername() {
        String email = "user@mail.com";
        String password = "password";
        JwtRequest request = new JwtRequest();
        request.setEmail(email);
        request.setPassword(password);
        User user = new User();
        user.setEmail(email);
        Mockito.when(userService.getByEmail(email))
                .thenThrow(ResourceNotFoundException.class);
        Mockito.verifyNoInteractions(tokenProvider);
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> authService.login(request));
    }

    @Test
    void refresh() {
        String refreshToken = "refreshToken";
        String accessToken = "accessToken";
        String newRefreshToken = "newRefreshToken";
        JwtResponse response = new JwtResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(newRefreshToken);
        Mockito.when(tokenProvider.refreshUserTokens(refreshToken))
                .thenReturn(response);
        JwtResponse testResponse = authService.refresh(refreshToken);
        Mockito.verify(tokenProvider).refreshUserTokens(refreshToken);
        Assertions.assertEquals(testResponse, response);
    }

}
