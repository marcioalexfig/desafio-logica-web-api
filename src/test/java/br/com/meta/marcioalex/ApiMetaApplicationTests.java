package br.com.meta.marcioalex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import br.com.meta.marcioalex.modelo.Contato;
import br.com.meta.marcioalex.service.ContatoService;

@SpringBootTest
class ApiMetaApplicationTests {

	@Autowired
	ContatoService contatoService;
	
	@Test
	@DisplayName("Verifica Criação")
	public void verificaGravacao() throws Exception {
		Contato contato = new Contato("Alex","telefone", "22222222", "teste obs");
		Contato contatoNovo = contatoService.gravarContato(contato);
		assertNotNull(contatoNovo);
		assertFalse(contatoNovo.getId().isBlank());
		contatoService.removerContato(contatoNovo);
	}
	 
	@Test
	@DisplayName("Verifica Listagem")
	public void verificaListagem() throws Exception {
		Page<Contato> listaPage = contatoService.listarContatos(0, 10);
		List<Contato> lista = listaPage.getContent();
		assertTrue(lista.size() <= 10);
	}
	@Test
	@DisplayName("Verifica Paginacao")
	public void verificaPaginacao() throws Exception {
		Page<Contato> listaPage = contatoService.listarContatos(0, 2);
		List<Contato> lista = listaPage.getContent();
		assertTrue(lista.size() == 2);
	}
	
	@Test
	@DisplayName("Verifica Remoção")
	public void verificaRemocao() throws Exception {
		Contato contato = new Contato("Alex Teste 123","telefone", "22222222", "teste obs");
		Contato contatoNovo = contatoService.gravarContato(contato);
		assertNotNull(contatoNovo);
		assertFalse(contatoNovo.getId().isBlank());
		contatoService.removerContato(contatoNovo);
		Contato contatoDeletado = contatoService.buscarPorNome("Alex Teste 123");
		assertNull(contatoDeletado);
	}
	@Test
	@DisplayName("Verifica Busca por Id")
	public void verificaBuscaPorId() throws Exception {
		Contato contato = new Contato("Alex Teste 123","telefone", "22222222", "teste obs");
		Contato contatoNovo = contatoService.gravarContato(contato);
		assertNotNull(contatoNovo);
		assertFalse(contatoNovo.getId().isBlank());
		Contato contatoPorId = contatoService.buscarPorID(contatoNovo.getId());
		assertEquals(contatoPorId.getNome(), "Alex Teste 123");
		contatoService.removerContato(contatoNovo);
	}

}
