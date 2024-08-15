package hinecora.net.TaskManagementSystem.config;

import hinecora.net.TaskManagementSystem.repository.TaskRepository;
import hinecora.net.TaskManagementSystem.repository.UserRepository;
import hinecora.net.TaskManagementSystem.service.impl.AuthServiceImpl;
import hinecora.net.TaskManagementSystem.service.impl.TaskServiceImpl;
import hinecora.net.TaskManagementSystem.service.impl.UserServiceImpl;
import hinecora.net.TaskManagementSystem.service.props.JwtProperties;
import hinecora.net.TaskManagementSystem.web.security.JwtTokenProvider;
import hinecora.net.TaskManagementSystem.web.security.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@TestConfiguration
@RequiredArgsConstructor
public class TestConfig {

    @Bean
    @Primary
    public BCryptPasswordEncoder testPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtProperties jwtProperties() {
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret(
                "dmdqYmhqbmttYmNhamNjZWhxa25hd2puY2xhZWtic3ZlaGtzYmJ1dg=="
        );
        return jwtProperties;
    }

    @Bean
    public UserDetailsService userDetailsService(
            final UserRepository userRepository
    ) {
        return new JwtUserDetailsService(userService(userRepository));
    }


    @Bean
    public Configuration configuration() {
        return Mockito.mock(Configuration.class);
    }


    @Bean
    public JwtTokenProvider tokenProvider(
            final UserRepository userRepository
    ) {
        return new JwtTokenProvider(jwtProperties(),
                userDetailsService(userRepository),
                userService(userRepository));
    }

    @Bean
    @Primary
    public UserServiceImpl userService(
            final UserRepository userRepository
    ) {
        return new UserServiceImpl(
                userRepository,
                testPasswordEncoder()
        );
    }

    @Bean
    @Primary
    public TaskServiceImpl taskService(
            final TaskRepository taskRepository
    ) {
        return new TaskServiceImpl(
                taskRepository,
                userService(userRepository())
        );
    }

    @Bean
    @Primary
    public AuthServiceImpl authService(
            final UserRepository userRepository,
            final AuthenticationManager authenticationManager
    ) {
        return new AuthServiceImpl(
                authenticationManager,
                tokenProvider(userRepository),
                userService(userRepository)
        );
    }

    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public TaskRepository taskRepository() {
        return Mockito.mock(TaskRepository.class);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return Mockito.mock(AuthenticationManager.class);
    }

}
