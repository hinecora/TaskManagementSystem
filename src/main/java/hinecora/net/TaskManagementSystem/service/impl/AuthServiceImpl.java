package hinecora.net.TaskManagementSystem.service.impl;

import hinecora.net.TaskManagementSystem.domain.user.User;
import hinecora.net.TaskManagementSystem.service.AuthService;
import hinecora.net.TaskManagementSystem.service.UserService;
import hinecora.net.TaskManagementSystem.web.dto.auth.JwtRequest;
import hinecora.net.TaskManagementSystem.web.dto.auth.JwtResponse;
import hinecora.net.TaskManagementSystem.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public JwtResponse login(
            JwtRequest loginRequest
    ) {
        JwtResponse jwtResponse = new JwtResponse();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword())
        );
        User user = userService.getByEmail(loginRequest.getEmail());
        jwtResponse.setId(user.getId());
        jwtResponse.setEmail(user.getEmail());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(
                user.getId(), user.getPassword(), user.getRoles())
        );
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(
                user.getId(), user.getPassword())
        );
        return jwtResponse;

    }

    @Override
    public JwtResponse refresh(
            String refreshToken
    ) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }

}
