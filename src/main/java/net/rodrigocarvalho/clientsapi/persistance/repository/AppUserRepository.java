package net.rodrigocarvalho.clientsapi.persistance.repository;

import net.rodrigocarvalho.clientsapi.persistance.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

    void deleteByUsername(String username);

    boolean existsByUsername(String username);
}