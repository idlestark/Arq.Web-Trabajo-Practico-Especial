package content.service;
import content.entities.Account;
import content.entities.User;
import content.repository.AccountRepository;
import content.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Account findAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Transactional
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }


    public void cancelAccount(Account account) {
        if (!account.getUsers().isEmpty()) {
            List<User> users = new ArrayList<>(account.getUsers());

            for (User user : users) {
                user.getAccounts().remove(account);
                userRepository.save(user);
            }

            account.getUsers().clear();
            account.setActive(false);

            accountRepository.save(account);
        }
    }


    public Account activateAccount(Long accountId, Long userId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (account == null || user == null) {
            return null;
        }

        if (!account.getUsers().contains(user)) {
            account.getUsers().add(user);
            account.setActive(Boolean.TRUE);
            user.getAccounts().add(account);
            userRepository.save(user);
            accountRepository.save(account);

        }
        return account;
    }

}

