package content.controller;
import content.entities.Account;
import content.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accountList = accountService.findAllAccounts();
        if (accountList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accountList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Long id) {
        Account account = accountService.findAccountById(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account CreatedAccount = accountService.saveAccount(account);
        if (CreatedAccount == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(CreatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") Long id, @RequestBody Account account) {
        Account existentAccount = accountService.findAccountById(id);

        if (existentAccount == null) {
            return ResponseEntity.notFound().build();
        }

        existentAccount.setRegistrationDate(account.getRegistrationDate());
        existentAccount.setBalance(account.getBalance());

        Account updatedAccount = accountService.updateAccount(existentAccount);

        return ResponseEntity.ok(updatedAccount);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Account> cancelAccount(@PathVariable("id") long id) {

        Account account = accountService.findAccountById(id);

        accountService.cancelAccount(account);

        return ResponseEntity.ok(account);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Account> activateAccount(@PathVariable("id") Long id, @RequestParam Long userId) {
        Account account = accountService.activateAccount(id, userId);
        if (account == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(account);
    }


}