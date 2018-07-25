package br.jus.trf1.aletheia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.trf1.aletheia.model.Demanda;

public interface DemandaRepository extends JpaRepository<Demanda, Long> {

}
