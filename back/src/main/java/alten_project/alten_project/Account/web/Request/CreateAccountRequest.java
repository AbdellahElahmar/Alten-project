package alten_project.alten_project.Account.web.Request;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class CreateAccountRequest {

    private String username;
    private String firstName;
    @Email
    private String email;
    private String password;
}
