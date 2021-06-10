package me.dedin.BancoDeDados.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import me.dedin.BancoDeDados.Conecta;
import me.dedin.BancoDeDados.DAO.RemedioDAO;
import me.dedin.BancoDeDados.model.Remedio;

public class RemedioController {
	private RemedioDAO remedioDAO;

	public RemedioController() throws SQLException {
		Connection connection = new Conecta().mantemConexao();
		this.remedioDAO = new RemedioDAO(connection);
	}

	public List<Remedio> listar() {
		return this.remedioDAO.listar();
	}

	public void alterar(Integer id, String nomeRemedio, Double preco, Integer idFornecimento, String tarja) {
		this.remedioDAO.alterar(id, nomeRemedio, preco, idFornecimento, tarja);
	}

	public void salvar(Remedio remedio) {
		this.remedioDAO.salvar(remedio);

	}

	public void deletar(Integer id) {
		this.remedioDAO.deletar(id);
	}

	public List<Remedio> buscar(String busca) throws SQLException {
		return this.remedioDAO.buscar(busca);
	}
}

