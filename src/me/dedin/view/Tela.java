/*tarja no TelaRemedio
 * erros
 * transacao que altere mais de uma tabela e integridade
 */

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

import me.dedin.BancoDeDados.controller.PessoaController;
import me.dedin.BancoDeDados.model.Pessoa;

public class Tela extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PessoaController pessoaController;
	private JLabel labelCpf, labelNome, labelSexo, labelTelefone, labelPesquisar;
	private JTextField textoCpf, textoNome, textoTelefone, textoPesquisa;
	private JComboBox<String> comboGeneros;
	private JButton botaoSalvar, botaoEditar, botaoLimpar, botarApagar, botaoPesquisar, botaoAtualizar;
	private JTable tabela;
	private DefaultTableModel modelo;
	String str = new String();
	
	

	public Tela() throws SQLException {
		super("Pessoas");
		Container container = getContentPane();
		setLayout(null);
		
		this.pessoaController = new PessoaController();

		setJMenuBar(Menuzin.menu());
		
		labelCpf = new JLabel("CPF");
		labelNome = new JLabel("Nome");
		labelSexo = new JLabel("Sexo");
		labelTelefone = new JLabel("Telefone");
		labelPesquisar = new JLabel("Pesquisa:");

		labelCpf.setBounds(10, 10, 240, 15);
		labelNome.setBounds(10, 50, 240, 15);
		labelSexo.setBounds(10, 90, 240, 15);
		labelTelefone.setBounds(125, 90, 240, 15);
		labelPesquisar.setBounds(400, 145, 80, 20);

		labelCpf.setForeground(Color.BLACK);
		labelNome.setForeground(Color.BLACK);
		labelSexo.setForeground(Color.BLACK);
		labelTelefone.setForeground(Color.BLACK);
		labelPesquisar.setForeground(Color.BLACK);

		container.add(labelCpf);
		container.add(labelNome);
		container.add(labelSexo);
		container.add(labelTelefone);
		container.add(labelPesquisar);

		textoCpf = new JTextField();
		textoNome = new JTextField();
		textoTelefone = new JTextField();
		textoPesquisa = new JTextField();
		comboGeneros = new JComboBox<String>();

		comboGeneros.addItem("Selecione");
		comboGeneros.addItem("M");
		comboGeneros.addItem("F");

		textoCpf.setBounds(10, 25, 265, 20);
		textoNome.setBounds(10, 65, 265, 20);
		comboGeneros.setBounds(10, 105, 90, 20);
		textoTelefone.setBounds(125, 105, 150, 20);
		textoPesquisa.setBounds(460, 145, 150, 20);

		container.add(textoCpf);
		container.add(textoNome);
		container.add(comboGeneros);
		container.add(textoTelefone);
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

		String[] columnNames = { "ID", "CPF", "Nome", "Sexo", "Telefone" };
		String[][] data = { { "ID", "CPF", "Nome", "Sexo", "Telefone" } };
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
		modelo.addColumn("CPF");
		modelo.addColumn("Nome");
		modelo.addColumn("Genero");
		modelo.addColumn("Telefone");
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
		if (!textoCpf.getText().equals("") && !textoNome.getText().equals("")
				&& !(comboGeneros.getSelectedIndex() == 0)) {
			String cpf = textoCpf.getText();
			String nome = textoNome.getText();
			String genero = (String) comboGeneros.getSelectedItem();
			Integer telefone = null;
			if (!(textoTelefone.getText().equals(""))) {
				telefone = Integer.parseInt(textoTelefone.getText().trim());
			}
			Pessoa pessoa = new Pessoa(cpf, nome, genero, telefone);
			this.pessoaController.salvar(pessoa);
			JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
			this.limpar();
		} else {
			JOptionPane.showMessageDialog(this, "Digite os dados corretamente");
		}
	}

	private void limparTabela() {
		modelo.getDataVector().clear();
	}

	private void preencherTabela(Integer op, String busca) throws SQLException {
		List<Pessoa> pessoas;
		if (op == 0) {
			pessoas = listarPessoa();
		} else {
			pessoas = buscarPessoa(busca);
		}
		try {
			for (Pessoa pessoa : pessoas) {
				modelo.addRow(new Object[] { pessoa.getId(), pessoa.getCpf(), pessoa.getNome(), pessoa.getGenero(),
						pessoa.getTelefone() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private List<Pessoa> buscarPessoa(String busca) throws SQLException {
		return this.pessoaController.buscar(busca);
	}

	private List<Pessoa> listarPessoa() throws SQLException {
		return this.pessoaController.listar();
	}

	private void alterar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), 0); // tabela.getSelectedColumn());
		if (objetoDaLinha instanceof Integer) {
			Integer id = (Integer) objetoDaLinha;
			String cpf = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
			String nome = (String) modelo.getValueAt(tabela.getSelectedRow(), 2);
			String genero = (String) modelo.getValueAt(tabela.getSelectedRow(), 3);
			Integer telefone = null;
			if (!modelo.getValueAt(tabela.getSelectedRow(), 4).equals("")) {
				telefone = (Integer) modelo.getValueAt(tabela.getSelectedRow(), 4);
			}
			this.pessoaController.alterar(id, cpf, nome, genero, telefone);
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, digite corretamente");
		}
	}

	private void limpar() {
		this.textoCpf.setText("");
		this.textoNome.setText("");
		this.comboGeneros.setSelectedIndex(0);
		this.textoTelefone.setText("");
	}

	private void deletar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), 0);
		if (objetoDaLinha instanceof Integer) {
			Integer id = (Integer) objetoDaLinha;
			this.pessoaController.deletar(id);
			modelo.removeRow(tabela.getSelectedRow());
			JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}
}