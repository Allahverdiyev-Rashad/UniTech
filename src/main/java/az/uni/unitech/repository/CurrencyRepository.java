package az.uni.unitech.repository;

import az.uni.unitech.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    List<Currency> findByUpdatedDateBefore(LocalDateTime localDateTime);

}