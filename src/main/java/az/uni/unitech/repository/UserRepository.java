package az.uni.unitech.repository;

import az.uni.unitech.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByPinEqualsIgnoreCase(String pin);

    Optional<User> findByUsernameIgnoreCase(String username);

}