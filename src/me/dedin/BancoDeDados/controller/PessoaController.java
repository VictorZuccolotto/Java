package me.dedin.BancoDeDados.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import me.dedin.BancoDeDados.Conecta;
import me.dedin.BancoDeDados.DAO.PessoaDAO;
import me.dedin.BancoDeDados.model.Pessoa;

public class PessoaController {

	private PessoaDAO pessoaDAO;

	public PessoaController() throws SQLException {
		Connection connection = new Conecta().mantemConexao();
		this.pessoaDAO = new PessoaDAO(connection);
	}

	public List<Pessoa> listar() {
		return this.pessoaDAO.listar();
	}

	public void alterar(Integer id, String cpf, String descricao, String genero, Integer telefone) {
		this.pessoaDAO.alterar(id, cpf, descricao, genero, telefone);
	}

	public void salvar(Pessoa pessoa) {
		this.pessoaDAO.salvar(pessoa);

	}

	public void deletar(Integer id) {
		this.pessoaDAO.deletar(id);
	}

	public List<Pessoa> buscar(String busca) throws SQLException {
		return this.pessoaDAO.buscar(busca);
	}
}
