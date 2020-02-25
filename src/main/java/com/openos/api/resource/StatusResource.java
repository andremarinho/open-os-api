package com.openos.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.openos.api.event.RecursoCriadoEvent;
import com.openos.api.model.Status;
import com.openos.api.repository.StatusRepository;
import com.openos.api.service.StatusService;

@RestController
@RequestMapping("/status")
public class StatusResource {

	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private ApplicationEventPublisher publish;
	
	@Autowired
	private StatusService statusService;

	@GetMapping
	public List<Status> listar() {
		return statusRepository.findAll();
	}

	
	@PostMapping
	public ResponseEntity<Status> criar(@RequestBody Status status, HttpServletResponse response) {

		Status statusSalvo = this.statusRepository.save(status);

		publish.publishEvent(new RecursoCriadoEvent(this, response, statusSalvo.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(statusSalvo);

	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo){
		
		Optional<Status> status = this.statusRepository.findById(codigo);
		
		return status.isPresent() ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
	}

	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		
		this.statusRepository.deleteById(codigo);
	}
	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Status> atualizar(@PathVariable Long codigo,@Valid @RequestBody Status status){
		
		Status statusSalvo = this.statusService.atualizar(codigo, status);
		return ResponseEntity.ok(statusSalvo);
		
	}
	
	
}
