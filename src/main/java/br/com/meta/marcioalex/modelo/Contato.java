package br.com.meta.marcioalex.modelo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

public class Contato implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@NotBlank(message = "Nome é obrigatório!")
	private String nome;
	@NotBlank(message = "Canal é obrigatório!")
	private String canal;
	@NotBlank(message = "Valor é obrigatório!")
	private String valor;
	private String obs;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public Contato(String nome, String canal, String valor, String obs) {
		super();
		this.nome = nome;
		this.canal = canal;
		this.valor = valor;
		this.obs = obs;
	}
	
}
