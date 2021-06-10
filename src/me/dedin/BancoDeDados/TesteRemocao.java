package me.dedin.BancoDeDados;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteRemocao {
	public static void main(String[] args) throws SQLException {
		Connection cnn = new Conecta().mantemConexao();
		
		Statement st = cnn.createStatement();
		st.execute("Delete from amigos where nome like '%alok%'");
		
		Integer linhas = st.getUpdateCount();
		System.out.println(linhas);
	}
}
