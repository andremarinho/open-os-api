package com.openos.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, Long> codigo;
	public static volatile SingularAttribute<Produto, String> nome;
	public static volatile SingularAttribute<Produto, String> referencia;

	public static final String CODIGO = "codigo";
	public static final String NOME = "nome";
	public static final String REFERENCIA = "referencia";

}

