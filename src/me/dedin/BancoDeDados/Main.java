/*
BANCO DE DADOS
CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON *.* TO 'username'@'localhost' WITH GRANT OPTION;

CREATE USER 'username'@'%' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON *.* TO 'username'@'%' WITH GRANT OPTION;

FLUSH PRIVILEGES;
 */

package me.dedin.BancoDeDados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) throws SQLException {
//			Class.forName("org.mariadb.jdbc.Driver");
			Conecta cnct = new Conecta();
			Connection con = cnct.mantemConexao();
			
			Statement stm = con.createStatement();
			stm.execute("SELECT nome FROM amigos");
			
			ResultSet rst = stm.getResultSet();
			
			while (rst.next()) {
				String td = rst.getString("nome");
				System.out.println(td);
				
			}
			
			
			
			
			
			con.close();
	
	}
}