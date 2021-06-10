package me.dedin.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menuzin {
	
		
	public static JMenuBar menu() {
	JMenuBar menuBar = new JMenuBar();

//	setJMenuBar(menuBar);

	JMenu fileMenu = new JMenu("Tabelas");
	menuBar.add(fileMenu);

	JMenuItem newAction = new JMenuItem("Pessoas");
	JMenuItem newAction2 = new JMenuItem("Remedios");
	
	fileMenu.add(newAction);
	fileMenu.add(newAction2);

	newAction.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Tela tela = new Tela();
				tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	
	newAction2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			TelaRemedios tela;
			try {
				tela = new TelaRemedios();
				tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	
	return menuBar;
	}
	
}
