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
import org.springframework.validation.annotation.Validated;
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
import com.openos.api.model.Produto;
import com.openos.api.repository.ProdutoRepository;
import com.openos.api.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	ApplicationEventPublisher publish;
	
	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<Produto> listar() {

		List<Produto> produtos = this.produtoRepository.findAll();

		return produtos;
	}

	@PostMapping
	public ResponseEntity<Produto> criar(@Valid @RequestBody  Produto produto, HttpServletResponse response) {
		Produto produtoSalvo = produtoRepository.save(produto);
		
		publish.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getCodigo()));
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
		
		Optional<Produto> produto = this.produtoRepository.findById(codigo);
		
		return produto.isPresent() ?  ResponseEntity.ok(produto): ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		
		this.produtoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long codigo, @Valid @RequestBody Produto produto){
		
		Produto produtoSalvo = this.produtoService.atualizar(codigo, produto);
		
		return ResponseEntity.ok(produtoSalvo);
		
	}
	
	
	

}
