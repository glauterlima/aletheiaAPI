package br.jus.trf1.aletheia.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<Sistema> criar(@RequestBody Sistema sistema, HttpServletResponse response){
		Sistema sistemaSalvo = sistemaRepository.save(sistema);	
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
			.buildAndExpand(sistemaSalvo.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(sistemaSalvo); 
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Sistema> buscarPeloCodigo(@PathVariable Long codigo){
		Sistema sistema = sistemaRepository.findOne(codigo);
		return sistema != null ? ResponseEntity.ok(sistema) : ResponseEntity.notFound().build();
	}

}
