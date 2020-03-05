package com.openos.api.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "os")
@Getter
@Setter
public class OrdemServico {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long codigo;
	
	@Column(name = "data_abertura")
	private LocalDate dataAbertura;
	
	@Column(name = "num_ordem_servico")
	private Long numOrdemServico;
	
	@Column(name = "observacao")
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "cod_produto")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "cod_cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "cod_status")
	private Status status;
	
	
	

}
