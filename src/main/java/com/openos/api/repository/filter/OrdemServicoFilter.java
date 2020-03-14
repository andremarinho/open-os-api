package com.openos.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrdemServicoFilter {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAberturaDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAberturaAte;
	
	private String observacao;
	
	
	

}
