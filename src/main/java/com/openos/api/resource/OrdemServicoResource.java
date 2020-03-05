package com.openos.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openos.api.model.OrdemServico;
import com.openos.api.repository.OrdemServicoRepository;

@RestController
@RequestMapping("/ordemservico")
public class OrdemServicoResource {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@GetMapping
	public List<OrdemServico> listar(){
		return this.ordemServicoRepository.findAll();
	}

}
