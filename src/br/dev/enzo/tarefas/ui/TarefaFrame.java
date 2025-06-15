package br.dev.enzo.tarefas.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputFilter.Status;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
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
	private JLabel labelStatus;
	private JTextField textCodigo;
	private JTextField textNome;
	private JTextField textDescricao;
	private JComboBox<String> comboFuncionario;
	private JComboBox<String> comboStatus;
	private JTextField textDataInicio;
	private JTextField textDataEntrega;
	private JTextField textPrazo;
	private JButton buttonSalvar;
	private JButton buttonSair;
	
	
	public TarefaFrame(JDialog owner) {
		criarTela(owner);
	}
	
	private void criarTela(JDialog owner) { 
		JDialog telaTarefa = new JDialog(owner, true);
		telaTarefa.setSize(500, 500);
		telaTarefa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaTarefa.setLayout(null);
		telaTarefa.setResizable(false);
		telaTarefa.setLocationRelativeTo(null);
		
		Container painel = telaTarefa.getContentPane();
		
		labelCodigo =  new JLabel("Código");
		labelCodigo.setBounds(10, 0, 150, 30);;
		textCodigo = new JTextField();
		textCodigo.setBounds(10, 30, 150, 30);
		textCodigo.setEnabled(false);
		textCodigo.setText(Utils.gerarUUID8());
		
		labelNome = new JLabel("Nome");
		labelNome.setBounds(10, 65, 150, 30);
		textNome =new JTextField();
		textNome.setBounds(10, 95, 150, 30);
		
		labelDescricao = new JLabel("Descrição");
		labelDescricao.setBounds(10, 130, 150, 30);
		textDescricao = new JTextField();
		textDescricao.setBounds(10, 160, 250, 30);
		
		labelFuncionario = new JLabel("Responsável");
		labelFuncionario.setBounds(10, 195, 150, 30);
		comboFuncionario = new JComboBox<String>();
		comboFuncionario.setBounds(10, 225, 200, 30);
		
		FuncionarioDAO dao = new FuncionarioDAO(null);
		List<Funcionario> funcionarios = dao.getFuncionarios();
		
		for(Funcionario f : funcionarios) {
			comboFuncionario.addItem(f.getNome() + " (" + f.getMatricula() + ")");
		}
		
		labelDataInicio = new JLabel("Data de Início");
		labelDataInicio.setBounds(10, 260, 150, 30);
		textDataInicio = new JTextField();
		textDataInicio.setBounds(10, 290, 150, 30);
				
		labelPrazo = new JLabel("Prazo(Dias)");
		labelPrazo.setBounds(10, 320, 150, 30);
		textPrazo = new JTextField();
		textPrazo.setBounds(10, 350, 150, 30);
		
		labelDataEntrega =  new JLabel("Data de Entrega");
		labelDataEntrega.setBounds(10, 380, 150, 30);
		textDataEntrega = new JTextField();
		textDataEntrega.setBounds(10, 410, 150, 30);
		
		String[] status = {"não iniciado", "em andamento", "em atraso", "concluído"};
		labelStatus = new JLabel("Status");
		labelStatus.setBounds(280, 260, 150, 30);
		comboStatus = new JComboBox<String>(status);
		comboStatus.setBounds(280, 290, 180, 30);
		comboStatus.setEnabled(false);
		
		buttonSalvar = new JButton("Salvar");
		buttonSalvar.setBounds(220, 410, 120, 40);
		buttonSalvar.setBackground(Color.GREEN);
		buttonSalvar.setOpaque(true);
		buttonSalvar.setBorderPainted(false);
		
		buttonSair =new JButton("Sair");
		buttonSair.setBounds(350, 410, 120, 40);
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
		painel.add(comboFuncionario);
		painel.add(labelDataInicio);
		painel.add(textDataInicio);
		painel.add(labelPrazo);
		painel.add(textPrazo);
		painel.add(labelDataEntrega);
		painel.add(textDataEntrega);
		painel.add(labelStatus);
		painel.add(comboStatus);
		painel.add(buttonSalvar);
		painel.add(buttonSair);
		
		comboFuncionario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == comboFuncionario) {
					
					comboFuncionario.getSelectedIndex();
				}
				
			}
		});
		
		
		buttonSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Tarefa t = new Tarefa(null);
				t.setCodigo(textCodigo.getText());
				t.setNome(textNome.getText());
				t.setDescricao(textDescricao.getText());
				t.setResponsavel(comboFuncionario.getSelectedIndex());
				t.setDataInicio(textDataInicio.getText());
				t.setPrazo(Integer.parseInt(textPrazo.getText()));
				t.setDataEntrega(textDataEntrega.getText());
				
				TarefaDAO dao = new TarefaDAO(t);
				boolean sucesso = dao.gravar();
				
				if(sucesso) {
					JOptionPane.showMessageDialog(telaTarefa, "Tarefa gravada com sucesso!");
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
		textDataInicio.setText(null);
		textNome.requestFocus();
	}
	
	
	
	
	
	
}
