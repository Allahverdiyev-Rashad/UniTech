package az.uni.unitech.repository;

import az.uni.unitech.domain.Account;
import az.uni.unitech.dto.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select new az.uni.unitech.dto.AccountDto(a.id,a.accountNumber,a.status,a.balance)" +
            " from Account a where a.user.id=:userId and a.status=true")
    List<AccountDto> allActiveAccountByUserId(Long userId);

    Optional<Account> findByAccountNumber(String accountNumber);

}