package com.bank.CustomerMs.repository;

import com.bank.CustomerMs.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
    Optional<Cliente> findByDni(String dni);
    boolean existsByDni(String dni);
}