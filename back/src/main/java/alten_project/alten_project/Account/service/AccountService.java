package alten_project.alten_project.Account.service;

import alten_project.alten_project.Account.domain.Account;
import alten_project.alten_project.Account.web.Request.CreateAccountRequest;
import alten_project.alten_project.Account.web.Request.JwtResponse;

public interface AccountService {

    void createAccount(CreateAccountRequest request);

    Account authenticate(String email, String rawPassword);

    JwtResponse login(String username, String password);


}
