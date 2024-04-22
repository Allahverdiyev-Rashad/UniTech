package az.uni.unitech.service;

import az.uni.unitech.error.ErrorMessage;
import az.uni.unitech.error.exception.DataNotFoundException;
import az.uni.unitech.error.exception.IllegalArgumentException;
import az.uni.unitech.repository.AccountRepository;
import az.uni.unitech.domain.Account;
import az.uni.unitech.dto.request.TransferRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void moneyTransfer(TransferRequest transferRequest) {
        log.info("moneyTransfer {}", transferRequest);
        Account fromAccount = accountRepository.findByAccountNumber(transferRequest.getFromAccountNumber()).orElseThrow(
                () -> DataNotFoundException.getInstance(ErrorMessage.NOT_FOUND.formatted("Payer")));
        Account toAccount = accountRepository.findByAccountNumber(transferRequest.getToAccountNumber()).orElseThrow(
                () -> DataNotFoundException.getInstance( ErrorMessage.NOT_FOUND.formatted("Recipient")));
        if (!fromAccount.getStatus() || !toAccount.getStatus()) {
            throw IllegalArgumentException.getInstance(ErrorMessage.DISABLE_ACCOUNT);
        }
        if (fromAccount.getAccountNumber().equals(toAccount.getAccountNumber())) {
            throw IllegalArgumentException.getInstance(ErrorMessage.SAME_ACCOUNT);
        }
        if (fromAccount.getBalance().compareTo(transferRequest.getAmount()) < 0) {
            throw IllegalArgumentException.getInstance(ErrorMessage.INSUFFICIENT_FUNDS);
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequest.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transferRequest.getAmount()));
        log.info("fromAccount {}", fromAccount);
        accountRepository.save(fromAccount);
        log.info("toAccount {}", toAccount);
        accountRepository.save(toAccount);
    }

}