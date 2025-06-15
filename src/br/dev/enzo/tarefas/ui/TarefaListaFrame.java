package br.dev.enzo.tarefas.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.dev.enzo.tarefas.dao.FuncionarioDAO;
import br.dev.enzo.tarefas.dao.TarefaDAO;
import br.dev.enzo.tarefas.model.Funcionario;
import br.dev.enzo.tarefas.model.Tarefa;

public class TarefaListaFrame {
	
	private JLabel labelTitulo;
	private JButton buttonNovo;
	private JButton buttonSair;
	private DefaultTableModel model; //dados da tabela
	private JTable tabelaTarefas; // tabela visualmente
	private JScrollPane scrollTarefas; // container da tabela
	String[] colunas = {"CÓDIGO","TAREFA", "DESCRIÇÃO","RESPONSÁVEL","INÍCIO","ENTREGA","STATUS"};
	
	public TarefaListaFrame(JFrame owner) {
		criarTela(owner);
	}
	
	private void criarTela(JFrame owner) {
		JDialog telaTarefaLista = new JDialog(owner, true);
		telaTarefaLista.setSize(700, 500);
		telaTarefaLista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaTarefaLista.setLayout(null);
		telaTarefaLista.setLocationRelativeTo(null);
		telaTarefaLista.setResizable(false);
		
		Container painel = telaTarefaLista.getContentPane();
		
		labelTitulo = new JLabel("Cadastro de Funcionários");
		labelTitulo.setBounds(10, 10, 500, 40);
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 32));
		labelTitulo.setForeground(Color.RED);
		
		// Criar a Tabela
		model = new DefaultTableModel(colunas, 100);
		tabelaTarefas = new JTable(model);
		scrollTarefas = new JScrollPane(tabelaTarefas);
		scrollTarefas.setBounds(10, 70, 680, 300);
		carregarDadosTabela();
		
		buttonNovo = new JButton();
		buttonNovo.setText("Cadastrar Nova Tarefa");
		buttonNovo.setBounds(10, 400, 250, 50);
		
		buttonSair = new JButton();
		buttonSair.setText("Sair");
		buttonSair.setBounds(270, 400, 150, 50);
		
		painel.add(labelTitulo);
		painel.add(scrollTarefas);
		painel.add(buttonNovo);
		painel.add(buttonSair);
		
		buttonNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TarefaFrame(telaTarefaLista);
				carregarDadosTabela();
				
			}
		});
		
		buttonSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(telaTarefaLista, "Sair do sistema?", "Atenção", JOptionPane.YES_NO_OPTION);
				if(resposta == 0) {
				System.exit(JFrame.DISPOSE_ON_CLOSE);
				}
			}
		});
		
		
		telaTarefaLista.setVisible(true);
		
	}
	
	private void carregarDadosTabela() {
		
		TarefaDAO dao = new TarefaDAO(null);
		List<Tarefa> tarefas = dao.getTarefas();
		
		int i = 0;
		
		Object[] [] dados = new Object[tarefas.size()][7];
		
		for(Tarefa t : tarefas) {
			dados[i] [0] = t.getCodigo();
			dados[i] [1] = t.getNome();
			dados[i] [2] = t.getDescricao();
			dados[i] [3] = t.getNomeResponsavel();
			dados[i] [4] = t.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			dados[i] [5] = t.getDataEntrega().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			dados[i] [6] = t.getStatus();
			i++;
		}
		
		model.setDataVector(dados, colunas);
		 
	}
	
	
	
}
