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
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "os")
@Getter
@Setter
public class OrdemServico {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;
	
	@NotNull
	@Column(name = "data_abertura")
	private LocalDate dataAbertura;
	
	@NotNull
	@Column(name = "num_ordem_servico")
	private Long numOrdemServico;
	
	@Column(name = "observacao")
	private String observacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cod_produto")
	private Produto produto;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cod_cliente")
	private Cliente cliente;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cod_status")
	private Status status;
	
	
	

}
