package com.openos.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.openos.api.model.Status;
import com.openos.api.repository.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	public Status atualizar(Long codigo, Status status) {
		
		Optional<Status> statusSalvo = this.statusRepository.findById(codigo);
		
		if(!statusSalvo.isPresent()) {
			
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(status, statusSalvo.get(), "codigo");
		
		return this.statusRepository.save(statusSalvo.get());
		
		
	}

}
