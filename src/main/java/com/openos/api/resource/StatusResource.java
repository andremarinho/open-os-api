package com.openos.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openos.api.model.Status;
import com.openos.api.repository.StatusRepository;

@RestController
@RequestMapping("/status")
public class StatusResource {
	
	@Autowired
	private StatusRepository statusRepository;
	
	@GetMapping
	public List<Status> listar(){
		return statusRepository.findAll();
	}

}
