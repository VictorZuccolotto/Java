package me.dedin.BancoDeDados.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import me.dedin.BancoDeDados.model.Remedio;

public class RemedioDAO {
	private Connection connection;

	public RemedioDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Remedio> listar() {
		List<Remedio> remedios = new ArrayList<Remedio>();
		try {
			String sql = "SELECT idremedio, nomeremedio, preco, idfornecimento, tarja FROM remedio";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				trasformarResultSetEmProduto(remedios, pstm);
			}
			return remedios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void trasformarResultSetEmProduto(List<Remedio> remedios, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Remedio remedio = new Remedio(rst.getInt(1), rst.getString(2), rst.getDouble(3), rst.getInt(4),
						rst.getString(5));
				remedios.add(remedio);
			}
		}
	}

	public void alterar(Integer id, String nomeRemedio, Double preco, Integer idFornecimento, String tarja) {
		try (PreparedStatement stm = connection.prepareStatement(
				"UPDATE remedio R SET R.nomeremedio = ?, R.preco = ?, R.idfornecimento = ?, R.tarja = ? WHERE idremedio = ?")) {
			stm.setString(1, nomeRemedio);
			stm.setDouble(2, preco);
			stm.setInt(3, idFornecimento);
			if (tarja == null) {
				stm.setNull(4, java.sql.Types.CHAR);
			} else {
				stm.setString(4, tarja);
			}
			stm.setInt(5, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void salvar(Remedio remedio) {
		try {
			String sql = "INSERT INTO remedio (nomeremedio, preco, idfornecimento, tarja) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, remedio.getNomeRemedio());
				pstm.setDouble(2, remedio.getPreco());
				pstm.setInt(3, remedio.getIdFornecimento());
				if (remedio.getTarja() == null) {
					pstm.setNull(4, java.sql.Types.INTEGER);
				} else {
					pstm.setString(4, remedio.getTarja());
				}
				pstm.execute();

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM remedio WHERE idremedio = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Remedio> buscar(String busca) throws SQLException {
		List<Remedio> remedios = new ArrayList<>();
		String sql1 = "SELECT idremedio, nomeremedio, preco, idfornecimento, tarja FROM remedio WHERE idremedio like ? OR nomeremedio like ? OR preco like ? OR idfornecimento like ? OR tarja like ?";
		try (PreparedStatement pstm = connection.prepareStatement(sql1)) {
			pstm.setString(1, "%"+busca+"%");
			pstm.setString(2, "%"+busca+"%");
			pstm.setString(3, "%"+busca+"%");
			pstm.setString(4, "%"+busca+"%");
			pstm.setString(5, "%"+busca+"%");
			pstm.execute();

			trasformarResultSetEmProduto(remedios, pstm);
		}
		return remedios;
	}
}
