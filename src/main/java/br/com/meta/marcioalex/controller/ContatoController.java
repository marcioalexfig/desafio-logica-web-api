package br.com.meta.marcioalex.controller;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meta.marcioalex.modelo.Contato;
import br.com.meta.marcioalex.service.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
	private Logger log = Logger.getLogger("ContatoController");
	
	@Autowired
	ContatoService contatoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Contato>> listarPaginado(
			@RequestParam(value="page", required=false) String page, 
			@RequestParam(value="size", required=false) String size
			) {
		Integer pagina = null;
		Integer tamanho = null;
		try {
			pagina 		= (page == null) ? 0 : Integer.valueOf(page);
			tamanho 	= (size == null) ? 10 : Integer.valueOf(size);
		} catch (NumberFormatException e) {
			log.info(e.getLocalizedMessage());
		}

		Page<Contato> paginaContatos = contatoService.listarContatos(pagina, tamanho);
		List<Contato> lista = paginaContatos.getContent();
		
		if (lista == null || lista.isEmpty()) return ResponseEntity.notFound().build(); 
		

		return ResponseEntity.ok(lista);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Contato> criar(@Valid @RequestBody Contato contato){
		if (contato.getId() != null ) return ResponseEntity.badRequest().build();
		Contato contatoNovo = contatoService.gravarContato(contato);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contatoNovo.getId()).toUri();
		return ResponseEntity.created( uri ).body( contatoNovo );
	}
	
	@GetMapping("/{idContato}")
	public ResponseEntity<Contato> buscar(@PathVariable String idContato){
		Contato contato = contatoService.buscarPorID(idContato);
		if ( contato == null ) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(contato);
	}
	
	@DeleteMapping("/{idContato}")
	public ResponseEntity<Contato> deletar(@PathVariable String idContato){
		Contato contato = contatoService.buscarPorID(idContato);
		if ( contato == null ) {
			return ResponseEntity.notFound().build();
		}
		
		contatoService.removerContato( contato );
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/{idContato}")
	public ResponseEntity<Contato> atualizar(@Valid @RequestBody Contato contato, @PathVariable String idContato){
		
		if (idContato.isBlank() || idContato.isEmpty() || idContato == null) {
			return ResponseEntity.noContent().build();
		}
		
		Contato contatoExistente = contatoService.buscarPorID(idContato);
		if ( contatoExistente == null ) {
			return ResponseEntity.notFound().build();
		}
		contatoExistente.setCanal(contato.getCanal());
		contatoExistente.setNome(contato.getNome());
		contatoExistente.setValor(contato.getValor());
		contatoExistente.setObs(contato.getObs());
		Contato contatoAtualizado = contatoService.atualizarContato( contatoExistente );
		
		return ResponseEntity.status( HttpStatus.OK).body( contatoAtualizado );
	}
	
}
