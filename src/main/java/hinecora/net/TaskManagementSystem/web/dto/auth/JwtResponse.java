package hinecora.net.TaskManagementSystem.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Login response")
public class JwtResponse {

    @Schema(description = "Id", example = "1")
    private long id;

    @Schema(description = "email", example = "johndoe@gmail.com")
    private String email;

    @Schema(description = "accessToken", example = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIkMmEkMTAkRTBZejIxaE53ei93SXV6djhDQ1QyZXpUc0FRbzY3LlRXQkppdHNQV2hnd3B6SEp2TmNXckciLCJpZCI6MSwicm9sZXMiOlsiUk9MRV9PV05FUiIsIlJPTEVfRVhFQ1VUT1IiXSwiZXhwIjoxNDY4MzY2Njc4OX0.LlzqNMwwpM9gGo4HyRElQCDM4M3MoUVXH8OL_TvjGSoRZkATD9m0-q5P9kHx8hAM")
    private String accessToken;

    @Schema(description = "refreshToken", example = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIkMmEkMTAkRTBZejIxaE53ei93SXV6djhDQ1QyZXpUc0FRbzY3LlRXQkppdHNQV2hnd3B6SEp2TmNXckciLCJpZCI6MSwiZXhwIjoyMjM5NTA1MjM2NjY3ODl9.VICCNn4KZzxVgbfJN9FwKyyO3t5ZXTGQsDVQ3WS4OEcjz10tzEjXfpPRG6HbXuOm")
    private String refreshToken;

}
