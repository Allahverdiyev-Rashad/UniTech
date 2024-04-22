package az.uni.unitech.service;

import az.uni.unitech.dto.AccountDto;
import az.uni.unitech.dto.response.AccountResponse;
import az.uni.unitech.dto.response.AccountResponseList;
import az.uni.unitech.error.ErrorMessage;
import az.uni.unitech.error.exception.AccountAlreadyExistsException;
import az.uni.unitech.error.exception.DataNotFoundException;
import az.uni.unitech.repository.AccountRepository;
import az.uni.unitech.repository.UserRepository;
import az.uni.unitech.domain.Account;
import az.uni.unitech.domain.User;
import az.uni.unitech.dto.request.AccountRequest;
import az.uni.unitech.mapper.AccountMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    public AccountResponse createAccount(Long userId, AccountRequest request) {
        log.info("accountRequest {}", request);
        User user = userRepository.findById(userId).orElseThrow(
                () -> DataNotFoundException.getInstance(ErrorMessage.NOT_FOUND.formatted("User")));
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(request.getAccountNumber());
        if (optionalAccount.isPresent()) {
            throw AccountAlreadyExistsException.getInstance(ErrorMessage.ACCOUNT_ALREADY_EXISTS);
        }
        Account account = accountMapper.toDomain(request);
        account.setUser(user);
        account.setStatus(true);
        log.info("createAccount {}", account);
        return accountMapper.toDto(accountRepository.save(account));
    }

    public AccountResponse changeStatusAccount(Long userId, AccountRequest request) {
        log.info("accountRequest {}", request);
        if (!userRepository.existsById(userId)) {
            throw DataNotFoundException.getInstance(ErrorMessage.NOT_FOUND.formatted("User"));
        }
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(request.getAccountNumber());
        if (optionalAccount.isEmpty()) {
            throw DataNotFoundException.getInstance(ErrorMessage.NOT_FOUND.formatted("Account"));
        }
        Account account = optionalAccount.get();
        account.setStatus(false);
        log.info("changeStatusAccount {}", account);
        return accountMapper.toDto(accountRepository.save(account));
    }

    public AccountResponseList allActiveAccounts(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> DataNotFoundException.getInstance(ErrorMessage.NOT_FOUND.formatted("User")));
        List<AccountDto> accountResponses = accountRepository.allActiveAccountByUserId(user.getId());
        AccountResponseList list = new AccountResponseList();
        list.setAccountResponseList(accountResponses);
        log.info("allActiveAccounts {}", list);
        return list;
    }

}