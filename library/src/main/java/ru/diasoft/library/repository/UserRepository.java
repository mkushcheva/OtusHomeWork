package ru.diasoft.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.library.domain.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByLogin(String login);
}
