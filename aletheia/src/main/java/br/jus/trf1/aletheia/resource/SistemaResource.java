package br.jus.trf1.aletheia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.trf1.aletheia.model.Sistema;
import br.jus.trf1.aletheia.repository.SistemaRepository;

@RestController
@RequestMapping("/sistemas")
public class SistemaResource {
	
	@Autowired
	private SistemaRepository sistemaRepository;
	
	@GetMapping
	public List<Sistema> listar() {
		return sistemaRepository.findAll();
	}

}
