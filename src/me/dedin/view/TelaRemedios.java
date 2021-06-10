package me.dedin.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import me.dedin.BancoDeDados.controller.RemedioController;
import me.dedin.BancoDeDados.model.Remedio;

public class TelaRemedios extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemedioController remedioController;
	private JLabel labelNomeRemedio, labelPreco, labelTarja, labelIdFornecimento, labelPesquisar;
	private JTextField textoNomeRemedio, textoPreco, textoIdFornecimento, textoPesquisa;
	private JComboBox<String> comboTarja;
	private JButton botaoSalvar, botaoEditar, botaoLimpar, botarApagar, botaoPesquisar, botaoAtualizar;
	private JTable tabela;
	private DefaultTableModel modelo;
	String str = new String();
	
	

	public TelaRemedios() throws SQLException {
		super("Remedios");
		Container container = getContentPane();
		setLayout(null);
		
		this.remedioController = new RemedioController();

		setJMenuBar(Menuzin.menu());
		
		labelNomeRemedio = new JLabel("Nome do remedio");
		labelPreco = new JLabel("Preço");
		labelTarja = new JLabel("Tarja");
		labelIdFornecimento = new JLabel("Id do fornecimento");
		labelPesquisar = new JLabel("Pesquisa:");

		labelNomeRemedio.setBounds(10, 10, 240, 15);
		labelPreco.setBounds(10, 50, 240, 15);
		labelTarja.setBounds(10, 90, 240, 15);
		labelIdFornecimento.setBounds(155, 90, 240, 15);
		labelPesquisar.setBounds(400, 145, 80, 20);

		labelNomeRemedio.setForeground(Color.BLACK);
		labelPreco.setForeground(Color.BLACK);
		labelTarja.setForeground(Color.BLACK);
		labelIdFornecimento.setForeground(Color.BLACK);
		labelPesquisar.setForeground(Color.BLACK);

		container.add(labelNomeRemedio);
		container.add(labelPreco);
		container.add(labelTarja);
		container.add(labelIdFornecimento);
		container.add(labelPesquisar);

		textoNomeRemedio = new JTextField();
		textoPreco = new JTextField();
		textoIdFornecimento = new JTextField();
		textoPesquisa = new JTextField();
		comboTarja = new JComboBox<String>();

		comboTarja.addItem("Sem tarja");
		comboTarja.addItem("Tarja Amarela");
		comboTarja.addItem("Tarja Vermelha");
		comboTarja.addItem("Tarja Preta");

		textoNomeRemedio.setBounds(10, 25, 265, 20);
		textoPreco.setBounds(10, 65, 265, 20);
		comboTarja.setBounds(10, 105, 120, 20);
		textoIdFornecimento.setBounds(155, 105, 120, 20);
		textoPesquisa.setBounds(460, 145, 150, 20);

		container.add(textoNomeRemedio);
		container.add(textoPreco);
		container.add(comboTarja);
		container.add(textoIdFornecimento);
		container.add(textoPesquisa);

		botaoSalvar = new JButton("Salvar");
		botaoLimpar = new JButton("Limpar");
		botaoPesquisar = new JButton(">");

		botaoSalvar.setBounds(10, 145, 80, 20);
		botaoLimpar.setBounds(100, 145, 80, 20);
		botaoPesquisar.setBounds(610, 145, 45, 20);

		container.add(botaoSalvar);
		container.add(botaoLimpar);
		container.add(botaoPesquisar);

		String[] columnNames = { "ID", "Nome do remedio", "Preço", "Id do fornecimento", "tarja" };
		String[][] data = { { "ID", "Nome do remedio", "Preço", "Id do fornecimento", "Tarja" } };
		JTable tabelaHeader = new JTable(data, columnNames);
		tabelaHeader.setBounds(10, 170, 742, 15);
		container.add(tabelaHeader);

		tabela = new JTable();
		modelo = (DefaultTableModel) tabela.getModel();

//		tabela.setBounds(10, 185, 760, 300);
		ScrollPane scroll = new ScrollPane();
		scroll.setBounds(10, 185, 760, 300);
		scroll.add(tabela);
		getContentPane().add(scroll);

		modelo.addColumn("Id");
		modelo.addColumn("Nome do remedio");
		modelo.addColumn("Preço");
		modelo.addColumn("Id do fornecimento");
		modelo.addColumn("Tarja");
		preencherTabela(0,null);
//		tabela.setBounds(10, 185, 760, 300);
//		container.add(tabela);

		botarApagar = new JButton("Excluir");
		botaoEditar = new JButton("Alterar");
		botaoAtualizar = new JButton("Atualizar");

		botarApagar.setBounds(10, 500, 80, 20);
		botaoEditar.setBounds(100, 500, 80, 20);
		botaoAtualizar.setBounds(600, 500, 100, 20);

		container.add(botarApagar);
		container.add(botaoEditar);
		container.add(botaoAtualizar);

		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		botaoEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alterar();
				limparTabela();
				try {
					preencherTabela(0, null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		botaoSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
				limparTabela();
				try {
					preencherTabela(0, null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		botarApagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deletar();
				limparTabela();
				try {
					preencherTabela(0, null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});

		botaoAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparTabela();
				try {
					preencherTabela(0, null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		botaoPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!textoPesquisa.getText().equals("")) {
					limparTabela();
					try {
						preencherTabela(0, null);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					limparTabela();
					try {
						preencherTabela(1, textoPesquisa.getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					erroPopUp();
				}

			}
		});

	}

	private void erroPopUp() {
		JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
	}

	private void salvar() {
		if (!textoNomeRemedio.getText().equals("") && !textoPreco.getText().equals("") 
				&& !textoIdFornecimento.getText().equals("")) {
			String nomeRemedio = textoNomeRemedio.getText();
			Double preco = Double.parseDouble(textoPreco.getText());
			String tarja = tarja();
			Integer idFornecimento = Integer.parseInt(textoIdFornecimento.getText().trim());
			Remedio remedio = new Remedio(nomeRemedio, preco, idFornecimento, tarja);
			this.remedioController.salvar(remedio);
			JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
			this.limpar();
		} else {
			JOptionPane.showMessageDialog(this, "Digite os dados corretamente.");
		}
	}
	
	private String tarja() {
		String str = (String) comboTarja.getSelectedItem();
		if(str.equals("Tarja Amarela")) {
			return "TA";
		}else if(str.equals("Tarja Vermelha")){
			return "TV";
		}else if(str.equals("Tarja Preta")) {
			return "TP";
		}else {
			return null;
		}
	}

	private void limparTabela() {
		modelo.getDataVector().clear();
	}

	private void preencherTabela(Integer op, String busca) throws SQLException {
		List<Remedio> remedios;
		if (op == 0) {
			remedios = listarProduto();
		} else {
			remedios = buscarProduto(busca);
		}
		try {
			for (Remedio remedio : remedios) {
				modelo.addRow(new Object[] { remedio.getId(), remedio.getNomeRemedio(), remedio.getPreco(), remedio.getIdFornecimento(),
						remedio.getTarja() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private List<Remedio> buscarProduto(String busca) throws SQLException {
		return this.remedioController.buscar(busca);
	}

	private List<Remedio> listarProduto() throws SQLException {
		return this.remedioController.listar();
	}

	private void alterar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), 0); // tabela.getSelectedColumn());
		if (objetoDaLinha instanceof Integer) {
			Integer id = (Integer) objetoDaLinha;
			String nomeRemedio = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
			Double preco = (Double) modelo.getValueAt(tabela.getSelectedRow(), 2);
			Integer idFornecimento = (Integer) modelo.getValueAt(tabela.getSelectedRow(), 3);
			String tarja = null;
			if (!modelo.getValueAt(tabela.getSelectedRow(), 4).equals("")) {
				tarja = (String) modelo.getValueAt(tabela.getSelectedRow(), 4);
			}
			this.remedioController.alterar(id, nomeRemedio, preco, idFornecimento, tarja);
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, digite corretamente");
		}
	}

	private void limpar() {
		this.textoNomeRemedio.setText("");
		this.textoPreco.setText("");
		this.comboTarja.setSelectedIndex(0);
		this.textoIdFornecimento.setText("");
	}

	private void deletar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), 0);
		if (objetoDaLinha instanceof Integer) {
			Integer id = (Integer) objetoDaLinha;
			this.remedioController.deletar(id);
			modelo.removeRow(tabela.getSelectedRow());
			JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}
}