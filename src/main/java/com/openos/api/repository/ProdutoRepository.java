package com.openos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openos.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
