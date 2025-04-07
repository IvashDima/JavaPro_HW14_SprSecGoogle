package org.example.springbank.repositories;

import org.example.springbank.models.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    List<Client> findByPattern(@Param("pattern") String pattern, Pageable pageable);

//     List<Client> findByNameOrEmailOrderById(String name, String email);
//     List<Client> findByNameAndEmail(String name, String email);

}
