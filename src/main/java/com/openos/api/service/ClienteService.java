package com.openos.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.openos.api.model.Cliente;
import com.openos.api.repository.ClienteRepository;

@Service
public class ClienteService {
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Cliente atualizar(Long codigo,Cliente cliente) {
		
		Optional<Cliente> clienteSalvo = buscarClientePeloCodigo(codigo);
		BeanUtils.copyProperties(cliente, clienteSalvo.get(), "codigo");
		
		return this.clienteRepository.save(clienteSalvo.get());
		
	}




	public void atualizarPropriedadeEndereco(Long codigo,String endereco) {
		
		Optional<Cliente> clienteSalvo = buscarClientePeloCodigo(codigo);
		clienteSalvo.get().setEndereco(endereco);
		
		this.clienteRepository.save(clienteSalvo.get());
	}
	
	

	private Optional<Cliente> buscarClientePeloCodigo(Long codigo) {
		Optional<Cliente> clienteSalvo = this.clienteRepository.findById(codigo);
		if(!clienteSalvo.isPresent()) {
			
			throw new EmptyResultDataAccessException(1);
		}
		return clienteSalvo;
	}
}
