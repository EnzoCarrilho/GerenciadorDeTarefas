package br.dev.enzo.tarefas.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.dev.enzo.tarefas.dao.FuncionarioDAO;
import br.dev.enzo.tarefas.dao.TarefaDAO;
import br.dev.enzo.tarefas.model.Funcionario;
import br.dev.enzo.tarefas.model.Tarefa;
import br.dev.enzo.tarefas.utils.Utils;

public class TarefaFrame {
	//faltando Status
	private JLabel labelCodigo;
	private JLabel labelNome;
	private JLabel labelDescricao;
	private JLabel labelFuncionario;
	private JLabel labelDataInicio;
	private JLabel labelDataEntrega;
	private JLabel labelPrazo;
	private JTextField textCodigo;
	private JTextField textNome;
	private JTextField textDescricao;
	private JTextField textFuncionario;
	private JTextField textDataInicio;
	private JTextField textDataEntrega;
	private JTextField textPrazo;
	private JButton buttonSalvar;
	private JButton buttonSair;
	
	public TarefaFrame(JFrame owner) {
		criarTela(owner);
	}
	
	private void criarTela(JFrame owner) { 
		JDialog telaTarefa = new JDialog(owner, true);
		telaTarefa.setSize(500, 500);
		telaTarefa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaTarefa.setLayout(null);
		telaTarefa.setResizable(false);
		telaTarefa.setLocationRelativeTo(null);
		
		Container painel = telaTarefa.getContentPane();
		
		labelCodigo =  new JLabel("Matrícula");
		labelCodigo.setBounds(10, 20, 150, 30);;
		textCodigo = new JTextField();
		textCodigo.setBounds(10, 50, 150, 30);
		textCodigo.setEnabled(false);
		textCodigo.setText(Utils.gerarUUID8());
		
		labelNome = new JLabel("Nome");
		labelNome.setBounds(10, 85, 150, 30);
		textNome =new JTextField();
		textNome.setBounds(10, 115, 350, 30);
		
		labelDescricao = new JLabel("Cargo");
		labelDescricao.setBounds(10, 150, 150, 30);
		textDescricao = new JTextField();
		textDescricao.setBounds(10, 180, 250, 30);
		
		labelFuncionario = new JLabel("Setor");
		labelFuncionario.setBounds(10, 215, 150, 30);
		textFuncionario = new JTextField();
		textFuncionario.setBounds(10, 245, 200, 30);
		
		labelDataInicio = new JLabel("Salário");
		labelDataInicio.setBounds(10, 280, 150, 30);
		textDataInicio = new JTextField();
		textDataInicio.setBounds(10, 310, 150, 30);
		
		//faltando dataEntrega Prazo e Status
		
		
		buttonSalvar = new JButton("Salvar");
		buttonSalvar.setBounds(10, 380, 200, 40);
		buttonSalvar.setBackground(Color.GREEN);
		buttonSalvar.setOpaque(true);
		buttonSalvar.setBorderPainted(false);
		
		buttonSair =new JButton("Sair");
		buttonSair.setBounds(220, 380, 120, 40);
		buttonSair.setBackground(Color.RED);
		buttonSair.setOpaque(true);
		buttonSair.setBorderPainted(false);
		
		painel.add(labelCodigo);
		painel.add(textCodigo);
		painel.add(labelNome);
		painel.add(textNome);
		painel.add(labelDescricao);
		painel.add(textDescricao);
		painel.add(labelFuncionario);
		painel.add(textFuncionario);
		painel.add(labelDataInicio);
		painel.add(textDataInicio);
		
		
		
		painel.add(buttonSalvar);
		painel.add(buttonSair);
		
		buttonSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Tarefa t = new Tarefa(null);
				t.setCodigo(textCodigo.getText());
				t.setDescricao(textDescricao.getText());
				//t.setResponsavel(textFuncionario.getText());
				t.setDataInicio(Utils.stringParaData(textDataInicio.getText()));
				
				
				
				TarefaDAO dao = new TarefaDAO(t);
				boolean sucesso = dao.gravar();
				
				if(sucesso) {
					JOptionPane.showMessageDialog(telaTarefa, "Funcionário gravado com sucesso!");
					limparFormulario();
				}else {
					JOptionPane.showMessageDialog(telaTarefa, "Ocorreu um erro na gravação.\nTente novamente.\nSe o problema persistir, entre em contato com o suporte");
				}
				
			}
		});
		
		buttonSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(telaTarefa, "Sair do sistema?", "Atenção", JOptionPane.YES_NO_OPTION);
				
				if(resposta == 0) {
				telaTarefa.dispose();
				}
			}
		});
		
		telaTarefa.setVisible(true);
	}  
	
	private void limparFormulario(){
		textCodigo.setText(Utils.gerarUUID8());
		textNome.setText(null);
		textDescricao.setText(null);
		textFuncionario.setText(null);
		textDataInicio.setText(null);
		textNome.requestFocus();
	}
	
	
	
	
	
}
