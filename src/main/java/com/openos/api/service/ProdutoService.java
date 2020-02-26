package com.openos.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.openos.api.model.Produto;
import com.openos.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public Produto atualizar(Long codigo, Produto produto) {
		
		Optional<Produto> produtoSalvo = this.produtoRepository.findById(codigo);
		
		if(!produtoSalvo.isPresent()) {
			
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(produto, produtoSalvo.get(),"codigo");
		
		return this.produtoRepository.save(produtoSalvo.get());
	}
}
