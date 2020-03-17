package com.openos.api.repository.ordemServico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.openos.api.model.OrdemServico;
import com.openos.api.model.OrdemServico_;
import com.openos.api.repository.filter.OrdemServicoFilter;

public class OrdemServicoRepositoryImpl implements OrdemServicoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<OrdemServico> filtrar(OrdemServicoFilter ordemServicoFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<OrdemServico> criteria = builder.createQuery(OrdemServico.class);

		Root<OrdemServico> root = criteria.from(OrdemServico.class);

		// Criação das restrições

		Predicate[] predicates = criarRestricoes(ordemServicoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<OrdemServico> query = manager.createQuery(criteria);

		
		adcionarRestricoesPaginacoesQuery(query, pageable);
		
		
		return new PageImpl<>(query.getResultList(), pageable, total(ordemServicoFilter));
	}




	private Predicate[] criarRestricoes(OrdemServicoFilter ordemServicoFilter, CriteriaBuilder builder,
			Root<OrdemServico> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(ordemServicoFilter.getObservacao())) {

			predicates.add(builder.like(builder.lower(root.get(OrdemServico_.OBSERVACAO)),
					"%" + ordemServicoFilter.getObservacao().toLowerCase() + "%"));

		}

		if (ordemServicoFilter.getDataAberturaDe() != null) {

			predicates.add(builder.greaterThanOrEqualTo(root.get(OrdemServico_.DATA_ABERTURA),
					ordemServicoFilter.getDataAberturaDe()));

		}

		if (ordemServicoFilter.getDataAberturaAte() != null) {

			predicates.add(builder.lessThanOrEqualTo(root.get(OrdemServico_.DATA_ABERTURA),
					ordemServicoFilter.getDataAberturaAte()));

		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adcionarRestricoesPaginacoesQuery(TypedQuery<OrdemServico> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalDeRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalDeRegistrosPorPagina);
		
		
	}
	
	
	private Long total(OrdemServicoFilter ordemServicoFilter) {
	
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<OrdemServico> root = criteria.from(OrdemServico.class);
		
		Predicate[] predicates = criarRestricoes(ordemServicoFilter, builder, root);
		
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		
		return manager.createQuery(criteria).getSingleResult();
	}
}
