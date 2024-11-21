package content.controller;
import content.entities.Account;
import content.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all accounts", description = "Gets a list of all registered accounts")
    @ApiResponse(responseCode = "200", description = "Accounts list obtained successfully")
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accountList = accountService.findAllAccounts();
        if (accountList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accountList);
    }

    @Operation(summary = "Get account by ID", description = "Gets a single account specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Long id) {
        Account account = accountService.findAccountById(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @Operation(summary = "Create account", description = "Create a new account")
    @ApiResponse(responseCode = "201", description = "Account created successfully")
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account CreatedAccount = accountService.saveAccount(account);
        if (CreatedAccount == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(CreatedAccount);
    }

    @Operation(summary = "Delete account", description = "Delete an existent account specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update account", description = "Updates an existent account with the given information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account updated successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
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

    @Operation(summary = "Cancel account", description = "Temporally disables an account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account canceled successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PutMapping("/cancel/{id}")
    public ResponseEntity<Account> cancelAccount(@PathVariable("id") long id) {

        Account account = accountService.findAccountById(id);

        accountService.cancelAccount(account);

        return ResponseEntity.ok(account);
    }

    @Operation(summary = "Activate account", description = "Reactivates a disabled account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account reactivated successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PutMapping("/activate/{id}")
    public ResponseEntity<Account> activateAccount(@PathVariable("id") Long id, @RequestParam Long userId) {
        Account account = accountService.activateAccount(id, userId);
        if (account == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(account);
    }


}