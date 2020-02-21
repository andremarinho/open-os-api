package com.openos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openos.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
