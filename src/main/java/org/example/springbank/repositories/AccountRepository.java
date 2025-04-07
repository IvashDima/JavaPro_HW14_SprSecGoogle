package org.example.springbank.repositories;

import org.example.springbank.models.Account;
import org.example.springbank.models.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT c FROM Account c WHERE LOWER(c.currency) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    List<Account> findByPattern(@Param("pattern") String pattern, Pageable pageable);

    @Query("SELECT c FROM Account c WHERE c.client = :client")
    List<Account> findByClient(@Param("client") Client client, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Account c WHERE c.client = :client")
    long countByClient(@Param("client") Client client);
}
