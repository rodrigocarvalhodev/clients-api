package net.rodrigocarvalho.clientsapi.persistance.repository;

import net.rodrigocarvalho.clientsapi.persistance.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}