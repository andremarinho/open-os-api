package com.openos.api.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrdemServico.class)
public abstract class OrdemServico_ {

	public static volatile SingularAttribute<OrdemServico, Cliente> cliente;
	public static volatile SingularAttribute<OrdemServico, Long> codigo;
	public static volatile SingularAttribute<OrdemServico, String> observacao;
	public static volatile SingularAttribute<OrdemServico, Produto> produto;
	public static volatile SingularAttribute<OrdemServico, Long> numOrdemServico;
	public static volatile SingularAttribute<OrdemServico, LocalDate> dataAbertura;
	public static volatile SingularAttribute<OrdemServico, Status> status;

	public static final String CLIENTE = "cliente";
	public static final String CODIGO = "codigo";
	public static final String OBSERVACAO = "observacao";
	public static final String PRODUTO = "produto";
	public static final String NUM_ORDEM_SERVICO = "numOrdemServico";
	public static final String DATA_ABERTURA = "dataAbertura";
	public static final String STATUS = "status";

}

