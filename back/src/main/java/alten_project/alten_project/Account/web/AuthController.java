package alten_project.alten_project.Account.web;

import alten_project.alten_project.Account.service.AccountService;
import alten_project.alten_project.Account.web.Request.AuthRequest;
import alten_project.alten_project.Account.web.Request.CreateAccountRequest;
import alten_project.alten_project.Account.web.Request.JwtResponse;
import alten_project.alten_project.jwt.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping()
@AllArgsConstructor
public class AuthController {


    private final AccountService accountService;


    @PostMapping("/token")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            JwtResponse response = accountService.login(authRequest.getEmail(), authRequest.getPassword());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/account")
    public void login(@RequestBody CreateAccountRequest request) {
        accountService.createAccount(request);
    }
}
