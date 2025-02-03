package alten_project.alten_project.Account.web.Request;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
