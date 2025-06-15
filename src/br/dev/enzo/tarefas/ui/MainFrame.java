package br.dev.enzo.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame {
	
	private JButton buttonFuncionarios;
	private JButton buttonTarefas;
		
	public MainFrame() {
		criarTela();
	}

	private void criarTela() {
			
		JFrame mainFrame = new JFrame("Gerenciador de tarefas");
			
		mainFrame.setSize(300, 120);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
			
		Container painel = mainFrame.getContentPane();
			
		buttonFuncionarios = new JButton("Funcionários");
		buttonFuncionarios.setBounds(20, 20, 120, 40);
			
		buttonTarefas = new JButton ("Tarefas");
		buttonTarefas.setBounds(150, 20, 120, 40);
			
		painel.add(buttonFuncionarios);
		painel.add(buttonTarefas);	
			
		mainFrame.setVisible(true);
		
		buttonFuncionarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FuncionarioListaFrame(mainFrame);
				
			}
		});
		
		buttonTarefas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TarefaListaFrame(mainFrame);
				
			}
		});

		
	}

}
