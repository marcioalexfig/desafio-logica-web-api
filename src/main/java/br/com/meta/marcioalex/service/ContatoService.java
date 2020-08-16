package br.com.meta.marcioalex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.meta.marcioalex.modelo.Contato;
import br.com.meta.marcioalex.repository.ContatoRepository;

@Service
public class ContatoService {
	@Autowired
	ContatoRepository contatoRepository;
	
	public Page<Contato> listarContatos(Integer pagina, Integer tamanho){
		Page<Contato> contatos = contatoRepository.findAll(PageRequest.of(pagina, tamanho, Sort.by("nome")));
		return contatos;
	}
	
	public Contato buscarPorID(String id) {
		return contatoRepository.findById( id );
	}
	
	public Contato buscarPorNome(String nome) {
		return contatoRepository.findByNome( nome );
	}
	
	public void removerContato(Contato contato) {
		contatoRepository.delete( contato );
	}
	
	public Contato gravarContato(Contato contato) {
		return contatoRepository.save( contato );
	}
	
	public Contato atualizarContato(Contato contato) {
		return contatoRepository.save( contato );
	}
}
