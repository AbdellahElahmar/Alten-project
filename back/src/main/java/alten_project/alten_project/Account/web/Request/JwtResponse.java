package alten_project.alten_project.Account.web.Request;

import lombok.Data;

@Data
public class JwtResponse {
    private Long id;
    private String token;
    private String email;

    public JwtResponse(Long id, String email, String token) {

        this.id = id;
        this.token = token;
        this.email = email;
    }

}