package br.jus.trf1.aletheia.repository.demanda;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.jus.trf1.aletheia.model.Demanda;
import br.jus.trf1.aletheia.model.Demanda_;
import br.jus.trf1.aletheia.repository.filter.DemandaFilter;

public class DemandaRepositoryImpl implements DemandaRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Demanda> filtrar(DemandaFilter demandaFilter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Demanda> criteria = builder.createQuery(Demanda.class);
		Root<Demanda> root = criteria.from(Demanda.class);
		
		//criar as restrições
		Predicate[] predicates = criarRestricoes(demandaFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Demanda> query = manager.createQuery(criteria);
	
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(DemandaFilter demandaFilter, CriteriaBuilder builder, Root<Demanda> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(demandaFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Demanda_.nome)),"%" + demandaFilter.getNome().toLowerCase() + "%"));
		}
		
		
		if(demandaFilter.getData() != null) {
			predicates.add(
					builder.equal(root.get(Demanda_.data), demandaFilter.getData()));	
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
