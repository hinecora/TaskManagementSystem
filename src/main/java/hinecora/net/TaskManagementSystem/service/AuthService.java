package hinecora.net.TaskManagementSystem.service;

import hinecora.net.TaskManagementSystem.web.dto.auth.JwtRequest;
import hinecora.net.TaskManagementSystem.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);

}
