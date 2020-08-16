package br.com.meta.marcioalex.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.meta.marcioalex.modelo.Contato;

public interface ContatoRepository  extends PagingAndSortingRepository<Contato, Integer> {
	
	Contato findById(String id);
	
	Contato findByNome(String nome);


}
