package alten_project.alten_project.Account.service.Impl;


import alten_project.alten_project.Account.domain.Account;
import alten_project.alten_project.Account.repository.AccountRepository;
import alten_project.alten_project.Account.service.AccountService;
import alten_project.alten_project.Account.web.Request.CreateAccountRequest;
import alten_project.alten_project.Account.web.Request.JwtResponse;
import alten_project.alten_project.jwt.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final JwtTokenUtil jwtUtil;

    public JwtResponse login(String username, String password) {
        Optional<Account> accountOpt = accountRepository.findByEmail(username);

        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();

            if (password.equals(account.getPassword())) {
                return new JwtResponse(account.getId(), account.getEmail(), jwtUtil.generateToken(username));
            } else {
                throw new RuntimeException("Mot de passe incorrect !");
            }
        } else {
            throw new RuntimeException("Utilisateur non trouvé !");
        }
    }


    @Override
    public void createAccount(CreateAccountRequest request) {
        Account account = new Account();
        account.setUserName(request.getUsername());
        account.setFirstName(request.getFirstName());
        account.setEmail(request.getEmail());
        account.setPassword(request.getPassword());
        accountRepository.save(account);
    }

    public Account authenticate(String email, String rawPassword) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return account;
    }
}
