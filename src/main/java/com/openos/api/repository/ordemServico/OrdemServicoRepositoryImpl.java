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

import org.springframework.util.StringUtils;

import com.openos.api.model.OrdemServico;
import com.openos.api.model.OrdemServico_;
import com.openos.api.repository.filter.OrdemServicoFilter;

public class OrdemServicoRepositoryImpl implements OrdemServicoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<OrdemServico> filtrar(OrdemServicoFilter ordemServicoFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<OrdemServico> criteria = builder.createQuery(OrdemServico.class);

		Root<OrdemServico> root = criteria.from(OrdemServico.class);

		// Criação das restrições
		
		Predicate[] predicates = criarRestricoes(ordemServicoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<OrdemServico> query = manager.createQuery(criteria);

		return query.getResultList();
	}

	private Predicate[] criarRestricoes(OrdemServicoFilter ordemServicoFilter, CriteriaBuilder builder,
			Root<OrdemServico> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(ordemServicoFilter.getObservacao())) {

			predicates.add(builder.like(builder.lower(root.get(OrdemServico_.OBSERVACAO)),
					"%" + ordemServicoFilter.getObservacao().toLowerCase() + "%"));

		}

		if (ordemServicoFilter.getDataAberturaDe() != null) {

			builder.greaterThanOrEqualTo(root.get(OrdemServico_.DATA_ABERTURA), ordemServicoFilter.getDataAberturaDe());

		}

		if (ordemServicoFilter.getDataAberturaAte() != null) {

			builder.lessThanOrEqualTo(root.get(OrdemServico_.DATA_ABERTURA), ordemServicoFilter.getDataAberturaAte());

		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
