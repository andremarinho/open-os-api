package com.openos.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.openos.api.model.Cliente;
import com.openos.api.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listar() {

		return this.clienteRepository.findAll();
	}

	
	@PostMapping
	public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente, HttpServletResponse response) {

		Cliente clienteReponse = this.clienteRepository.save(cliente);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(clienteReponse.getCodigo()).toUri();
		
		response.setHeader("location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(clienteReponse);
	
	}

}
