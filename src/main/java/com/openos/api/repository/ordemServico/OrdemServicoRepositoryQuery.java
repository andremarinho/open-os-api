package com.openos.api.repository.ordemServico;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.openos.api.model.OrdemServico;
import com.openos.api.repository.filter.OrdemServicoFilter;

public interface OrdemServicoRepositoryQuery  {
	
	
	public Page<OrdemServico> filtrar(OrdemServicoFilter ordemServicoFilter, Pageable pageable);

}
