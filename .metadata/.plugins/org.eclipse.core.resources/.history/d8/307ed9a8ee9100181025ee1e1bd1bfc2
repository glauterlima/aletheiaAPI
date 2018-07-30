package br.jus.trf1.aletheia.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.jus.trf1.aletheia.model.Demanda;
import br.jus.trf1.aletheia.repository.DemandaRepository;

@Service
public class DemandaService {
	
	@Autowired
	private DemandaRepository demandaRepository;
	
	public Demanda atualizar(Long codigo, Demanda demanda) {
		
		Demanda demandaSalva = demandaRepository.findOne(codigo);
		if (demandaSalva == null){
			throw new EmptyResultDataAccessException(1);
		}
			BeanUtils.copyProperties(demanda, demandaSalva, "codigo");
			return demandaRepository.save(demandaSalva);

	}
}
