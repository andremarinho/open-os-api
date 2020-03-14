package com.openos.api.repository.ordemServico;

import java.util.List;

import com.openos.api.model.OrdemServico;
import com.openos.api.repository.filter.OrdemServicoFilter;

public interface OrdemServicoRepositoryQuery  {
	
	
	public List<OrdemServico> filtrar(OrdemServicoFilter ordemServicoFilter);

}
