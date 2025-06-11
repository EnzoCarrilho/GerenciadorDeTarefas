package br.dev.enzo.tarefas.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.dev.enzo.tarefas.dao.FuncionarioDAO;
import br.dev.enzo.tarefas.model.Funcionario;

public class FuncionarioListaFrame {
	
	private JLabel labelTitulo;
	private JButton buttonNovo;
	private JButton buttonSair;
	private DefaultTableModel model; //dados da tabela
	private JTable tabelaFuncionarios; // tabela visualmente
	private JScrollPane scrollFuncionarios; // container da tabela
	String[] colunas = {"CÓDIGO","NOME FUNCIONÁRIO", "CARGO"};
	
	public FuncionarioListaFrame() {
		criarTela();
	}
	
	private void criarTela() {
		JFrame telaFuncionarioLista = new JFrame("Lista de funcionários");
		telaFuncionarioLista.setSize(700, 500);
		telaFuncionarioLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaFuncionarioLista.setLayout(null);
		telaFuncionarioLista.setLocationRelativeTo(null);
		telaFuncionarioLista.setResizable(false);
		
		Container painel = telaFuncionarioLista.getContentPane();
		
		labelTitulo = new JLabel("Cadastro de Funcionários");
		labelTitulo.setBounds(10, 10, 500, 40);
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 32));
		labelTitulo.setForeground(Color.RED);
		
		// Criar a Tabela
		model = new DefaultTableModel(colunas, 100);
		tabelaFuncionarios = new JTable(model);
		scrollFuncionarios = new JScrollPane(tabelaFuncionarios);
		scrollFuncionarios.setBounds(10, 70, 680, 300);
		carregarDadosTabela();
		
		buttonNovo = new JButton();
		buttonNovo.setText("Cadastrar Novo Funcionário");
		buttonNovo.setBounds(10, 400, 250, 50);
		
		buttonSair = new JButton();
		buttonSair.setText("Sair");
		buttonSair.setBounds(270, 400, 150, 50);
		
		painel.add(labelTitulo);
		painel.add(scrollFuncionarios);
		painel.add(buttonNovo);
		painel.add(buttonSair);
		
		buttonNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FuncionarioFrame(telaFuncionarioLista);
				carregarDadosTabela();
				
			}
		});
		
		buttonSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(telaFuncionarioLista, "Sair do sistema?", "Atenção", JOptionPane.YES_NO_OPTION);
				if(resposta == 0) {
				System.exit(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
		
		
		telaFuncionarioLista.setVisible(true);
		
	}
	
	private void carregarDadosTabela() {
		
		FuncionarioDAO dao = new FuncionarioDAO(null);
		List<Funcionario> funcionarios = dao.getFuncionarios();
		
		int i = 0;
		
		Object[] [] dados = new Object[funcionarios.size()][3];
		
		for(Funcionario f : funcionarios) {
			dados[i] [0] = f.getMatricula();
			dados[i] [1] = f.getNome();
			dados[i] [2] = f.getCargo();
			i++;
		}
		
		model.setDataVector(dados, colunas);
		 
	}
	
	
	

	
	
	
	
	
	
	
}
