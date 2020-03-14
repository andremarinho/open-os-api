package com.openos.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openos.api.event.RecursoCriadoEvent;
import com.openos.api.model.OrdemServico;
import com.openos.api.repository.OrdemServicoRepository;
import com.openos.api.repository.filter.OrdemServicoFilter;
import com.openos.api.service.OrdemServicoService;

@RestController
@RequestMapping("/ordemservico")
public class OrdemServicoResource {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ApplicationEventPublisher publish;
	
	@Autowired
	OrdemServicoService ordemServicoService;
	
	@GetMapping
	public List<OrdemServico> pesquisar(OrdemServicoFilter ordemServicoFilter){
		return this.ordemServicoRepository.filtrar(ordemServicoFilter);
	}
	
	
	@PostMapping
	public ResponseEntity<OrdemServico> criar(@Valid @RequestBody OrdemServico ordemServico, HttpServletResponse response){
		
		OrdemServico ordemServicoSalva = ordemServicoService.salvar(ordemServico);
		
		this.publish.publishEvent(new RecursoCriadoEvent(this, response, ordemServicoSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ordemServicoSalva);
		
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo){
		
		Optional<OrdemServico> ordemServico = this.ordemServicoRepository.findById(codigo);
		
		return ordemServico.isPresent() ? ResponseEntity.ok(ordemServico) : ResponseEntity.notFound().build();
	}

}
