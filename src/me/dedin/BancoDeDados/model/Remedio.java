package me.dedin.BancoDeDados.model;

public class Remedio {
	private Integer Id;
	private String nomeRemedio;
	private Double preco;
	private Integer IdFornecimento;
	private String tarja;
	
	public Remedio(Integer id, String nomeRemedio, Double preco, Integer IdFornecimento, String tarja) {
		this.Id= id;
		this.nomeRemedio=nomeRemedio;
		this.preco=preco;
		this.IdFornecimento=IdFornecimento;
		this.tarja=tarja;
	}
	public Remedio(String nomeRemedio, Double preco, Integer IdFornecimento, String tarja) {
		this.nomeRemedio=nomeRemedio;
		this.preco=preco;
		this.IdFornecimento=IdFornecimento;
		this.tarja=tarja;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getNomeRemedio() {
		return nomeRemedio;
	}
	public void setNomeRemedio(String nomeRemedio) {
		this.nomeRemedio = nomeRemedio;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Integer getIdFornecimento() {
		return IdFornecimento;
	}
	public void setIdFornecimento(Integer idFornecimento) {
		IdFornecimento = idFornecimento;
	}
	public String getTarja() {
		return tarja;
	}
	public void setTarja(String tarja) {
		this.tarja = tarja;
	}
}
