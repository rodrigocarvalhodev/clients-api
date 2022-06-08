package net.rodrigocarvalho.clientsapi.persistance.repository;

import net.rodrigocarvalho.clientsapi.persistance.entity.ServiceProvided;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, Long> {

    @Query("SELECT s FROM ServiceProvided s JOIN s.client c WHERE UPPER(c.name) LIKE UPPER( :name ) AND MONTH(s.providedTime) =:month   ")
    List<ServiceProvided> findByClientNameAndMonth(@Param("name") String name, @Param("month") Integer month);
}