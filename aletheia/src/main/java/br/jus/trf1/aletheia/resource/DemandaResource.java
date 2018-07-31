package br.jus.trf1.aletheia.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.jus.trf1.aletheia.event.RecursoCriadoEvent;
import br.jus.trf1.aletheia.exceptionhandler.AletheiaExceptionHandler.Erro;
import br.jus.trf1.aletheia.model.Demanda;
import br.jus.trf1.aletheia.repository.DemandaRepository;
import br.jus.trf1.aletheia.repository.filter.DemandaFilter;
import br.jus.trf1.aletheia.service.DemandaService;
import br.jus.trf1.aletheia.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/demandas")
public class DemandaResource {
	
	@Autowired
	private DemandaRepository demandaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private DemandaService demandaService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public List<Demanda> pesquisar(DemandaFilter demandaFilter) {
		return demandaRepository.filtrar(demandaFilter);
	}
	
	@PostMapping
	public ResponseEntity<Demanda> criar(@Valid @RequestBody Demanda demanda, HttpServletResponse response){
		Demanda demandaSalva = demandaService.salvar(demanda);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, demandaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(demandaSalva); 
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Demanda> buscarPeloCodigo(@PathVariable Long codigo){
		Demanda demanda = demandaRepository.findOne(codigo);
		return demanda != null ? ResponseEntity.ok(demanda) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo){
		demandaRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Demanda> atualizar(@PathVariable Long codigo, @Valid @RequestBody Demanda demanda) {
	
		Demanda demandaSalva = demandaService.atualizar(codigo, demanda);
		return ResponseEntity.ok(demandaSalva);
		
	}
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

}
