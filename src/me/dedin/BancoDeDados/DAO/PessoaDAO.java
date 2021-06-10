package me.dedin.BancoDeDados.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import me.dedin.BancoDeDados.model.Pessoa;

public class PessoaDAO {
	private Connection connection;

	public PessoaDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Pessoa> listar() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		try {
			String sql = "SELECT idpessoa, cpf, nome, genero, telefone FROM pessoa";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				trasformarResultSetEmProduto(pessoas, pstm);
			}
			return pessoas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void trasformarResultSetEmProduto(List<Pessoa> pessoas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Pessoa pessoa = new Pessoa(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),
						rst.getInt(5));
				pessoas.add(pessoa);
			}
		}
	}

	public void alterar(Integer id, String cpf, String nome, String genero, Integer telefone) {
		try (PreparedStatement stm = connection.prepareStatement(
				"UPDATE pessoa P SET P.cpf = ?, P.nome = ?, P.genero = ?, P.telefone = ? WHERE idpessoa = ?")) {
			stm.setString(1, cpf);
			stm.setString(2, nome);
			stm.setString(3, genero);
			if (telefone == null) {
				stm.setNull(4, java.sql.Types.INTEGER);
			} else {
				stm.setInt(4, telefone);
			}
			stm.setInt(5, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void salvar(Pessoa pessoa) {
		try {
			String sql = "INSERT INTO pessoa (cpf, nome, genero, telefone) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, pessoa.getCpf());
				pstm.setString(2, pessoa.getNome());
				pstm.setString(3, pessoa.getGenero());
				if (pessoa.getTelefone() == null) {
					pstm.setNull(4, java.sql.Types.INTEGER);
				} else {
					pstm.setInt(4, pessoa.getTelefone());
				}
				pstm.execute();

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM pessoa WHERE idpessoa = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Pessoa> buscar(String busca) throws SQLException {
		List<Pessoa> pessoas = new ArrayList<>();
		String sql1 = "SELECT idpessoa, cpf, nome, genero, telefone FROM pessoa WHERE idpessoa like ? OR cpf like ? OR nome like ? OR genero like ? OR telefone like ?";
		try (PreparedStatement pstm = connection.prepareStatement(sql1)) {
			pstm.setString(1, "%"+busca+"%");
			pstm.setString(2, "%"+busca+"%");
			pstm.setString(3, "%"+busca+"%");
			pstm.setString(4, "%"+busca+"%");
			pstm.setString(5, "%"+busca+"%");
			pstm.execute();

			trasformarResultSetEmProduto(pessoas, pstm);
		}
		return pessoas;
	}

}
