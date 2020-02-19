package com.openos.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.openos.api.model.Produto;
import com.openos.api.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> listar() {

		List<Produto> produtos = this.produtoRepository.findAll();

		return produtos;
	}

	@PostMapping
	public ResponseEntity<Produto> criar(@Valid @RequestBody  Produto produto, HttpServletResponse response) {
		Produto produtoResponse = produtoRepository.save(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(produtoResponse.getCodigo()).toUri();
		
		response.setHeader("location", uri.toASCIIString());
		return  ResponseEntity.created(uri).body(produtoResponse);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
		
		Optional<Produto> produto = this.produtoRepository.findById(codigo);
		
		return produto.isPresent() ?  ResponseEntity.ok(produto): ResponseEntity.notFound().build();
	}

}
