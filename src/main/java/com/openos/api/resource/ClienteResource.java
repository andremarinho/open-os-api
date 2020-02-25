package com.openos.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
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
import com.openos.api.model.Cliente;
import com.openos.api.repository.ClienteRepository;
import com.openos.api.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ApplicationEventPublisher publish;
	
	@Autowired
	ClienteService clienteService;

	@GetMapping
	public List<Cliente> listar() {

		return this.clienteRepository.findAll();
	}

	
	@PostMapping
	public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente, HttpServletResponse response) {

		Cliente clienteSalvo = this.clienteRepository.save(cliente);

		
		publish.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getCodigo() ));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo){
		
		Optional<Cliente> cliente = this.clienteRepository.findById(codigo);
		
		return cliente.isPresent()? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		
		this.clienteRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long codigo, @Valid @RequestBody Cliente cliente){
		
		Cliente clienteSalvo = this.clienteService.atualizar(codigo, cliente);
		
		return ResponseEntity.ok(clienteSalvo);
			
	}
	
	@PutMapping("/{codigo}/endereco")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarEndereco(@PathVariable Long codigo, @RequestBody String endereco) {
		
		this.clienteService.atualizarPropriedadeEndereco(codigo, endereco);
		
	}
	
	

}
