package com.openos.api.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openos.api.model.OrdemServico;
import com.openos.api.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
	
	
	@Autowired
	OrdemServicoRepository ordemServicoRepository;

	public OrdemServico salvar(@Valid OrdemServico ordemServico) {
		
		return this.ordemServicoRepository.save(ordemServico);
	}

}
