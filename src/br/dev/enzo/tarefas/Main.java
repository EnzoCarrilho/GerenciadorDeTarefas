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
import br.dev.enzo.tarefas.utils.Utils;

public class Main {

	public static void main(String[] args) {
		
		new FuncionarioListaFrame();
		
		
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

	
	
	//testarLeituraEscriraArquivo();
	private static void testarLeituraEscriraArquivo() {
		String so = System.getProperty("os.name");
		System.out.println(so);
		
		String path = "/Users/25132408/projetoTarefas/funcionarios";
		
		FileReader fr = null;
		BufferedReader br = null;
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			
			fw = new FileWriter(path, true);
			bw = new BufferedWriter(fw);
			
			bw.append("Enzo\n");
			bw.flush();
			
			String linha = br.readLine();
			
			while(linha != null) {
				System.out.println(linha);
				linha = br.readLine();			
		    }
			
		
		} catch (FileNotFoundException exception) {
			System.out.println("Arquivo nao encontrado!");
		}catch (IOException exception) {
			System.out.println("O arquivo está protegido para leitura!");
		}catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	

}
