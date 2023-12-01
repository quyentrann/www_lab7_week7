package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.entity.Login;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.services.AccountService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@CrossOrigin("*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PutMapping
    public boolean update(@RequestBody Account account) {
        return accountService.update(account) != null;
    }

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account findByID(@PathVariable long id) {
        return accountService.findByID(id).orElse(null);
    }

    @PostMapping("/login")
    public Account login(@RequestBody Login login) {
        return accountService.login(login).orElse(null);
    }

    @GetMapping("/current-account-id")
    public long getCurrentAccountID() {
        return accountService.getCurrentAccountID();
    }

    @GetMapping("/get-by-email/{email}")
    public Account getCurrentAccountID(@PathVariable String email) {
        return accountService.findByEmail(email).orElse(null);
    }
}
