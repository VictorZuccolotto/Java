package me.dedin.teste;

import java.sql.SQLException;

import javax.swing.JFrame;

import me.dedin.view.Tela;

public class Teste {
	public static void main(String[] args) throws SQLException {
		Tela tela = new Tela();
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
