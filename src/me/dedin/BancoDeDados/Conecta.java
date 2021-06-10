package me.dedin.BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conecta {
//jdbc:mariadb://192.168.0.37:3306/Dedo?user=dedin&password=dedin123
		public Connection mantemConexao() throws SQLException {
			return DriverManager
			.getConnection("jdbc:mariadb://www.dedin.me:3306/farmacia?user=dedin&password=dedin123");
		}
}
