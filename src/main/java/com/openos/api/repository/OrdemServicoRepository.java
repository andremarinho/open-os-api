package com.openos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openos.api.model.OrdemServico;
import com.openos.api.repository.ordemServico.OrdemServicoRepositoryQuery;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>,  OrdemServicoRepositoryQuery {

}
