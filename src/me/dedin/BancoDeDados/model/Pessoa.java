package me.dedin.BancoDeDados.model;

public class Pessoa {

	private Integer id;
	private String cpf;
	private String nome;
	private String genero;
	private Integer telefone;
	
	
	public Pessoa(Integer id, String cpf, String nome, String genero, Integer telefone) {
		this.id=id;
		this.cpf=cpf;
		this.nome=nome;
		this.genero=genero;
		this.telefone=telefone;
	}
	
	public Pessoa(String cpf, String nome, String genero, Integer telefone) {
		this.cpf=cpf;
		this.nome=nome;
		this.genero=genero;
		this.telefone=telefone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
}
