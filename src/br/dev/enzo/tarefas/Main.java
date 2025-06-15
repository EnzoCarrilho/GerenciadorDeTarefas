package br.dev.enzo.tarefas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import br.dev.enzo.tarefas.dao.FuncionarioDAO;
import br.dev.enzo.tarefas.model.Funcionario;
import br.dev.enzo.tarefas.model.Status;
import br.dev.enzo.tarefas.model.Tarefa;
import br.dev.enzo.tarefas.ui.FuncionarioFrame;
import br.dev.enzo.tarefas.ui.FuncionarioListaFrame;
import br.dev.enzo.tarefas.ui.MainFrame;
import br.dev.enzo.tarefas.ui.TarefaFrame;
import br.dev.enzo.tarefas.ui.TarefaListaFrame;
import br.dev.enzo.tarefas.utils.Utils;

public class Main {

	public static void main(String[] args) {
		
		//new TarefaListaFrame(null);
		
		new MainFrame();
		//new TarefaFrame(null);
		//new FuncionarioListaFrame();
		
		
		
//		FuncionarioDAO dao = new FuncionarioDAO(null);
//		dao.getFuncionarios();
		
		//new FuncionarioFrame();
		
//		Funcionario funcionario = new Funcionario("Ana Maria", "DBA");
//		funcionario.setSetor("Tecnologia da Informação");
//		funcionario.setSalario(8987.9);
//		
//		FuncionarioDAO dao = new FuncionarioDAO(funcionario);
//		dao.gravar();
	}


}
