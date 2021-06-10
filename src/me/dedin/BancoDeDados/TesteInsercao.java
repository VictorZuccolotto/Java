package me.dedin.BancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteInsercao {
	public static void main(String[] args) throws SQLException {
		Conecta cnct = new Conecta();
		try (Connection con = cnct.mantemConexao()) {
			con.setAutoCommit(false);

			try (PreparedStatement st = con.prepareStatement("INSERT INTO amigos(nome) VALUES (?)")) {

				insere("pereira", st);
//		ResultSet rs = st.getGeneratedKeys();
//		while (rs.next()) {
//			String str = rs.getString(1);
//			System.out.println("Teste tes t"+ str);
//		}
				con.commit();
			}
		}
	}

	private static void insere(String nome, PreparedStatement st) throws SQLException {
		st.setString(1, nome);
		st.execute();
	}
}
