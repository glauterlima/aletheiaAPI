package br.jus.trf1.aletheia.repository.demanda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.trf1.aletheia.model.Demanda;
import br.jus.trf1.aletheia.repository.filter.DemandaFilter;

public interface DemandaRepositoryQuery {
	
	public Page<Demanda> filtrar(DemandaFilter demandaFilter, Pageable pageable);

}
