package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    
    Optional<ClientEntity> findByClientId(String clientId);
    
    @Query("SELECT c FROM ClientEntity c WHERE " +
           "LOWER(c.clientId) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<ClientEntity> searchClients(@Param("query") String query);
    
    @Query("SELECT c.clientId FROM ClientEntity c")
    List<String> findAllClientIds();
}
