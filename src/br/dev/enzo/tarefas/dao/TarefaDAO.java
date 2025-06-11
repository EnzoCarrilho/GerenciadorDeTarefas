package br.dev.enzo.tarefas.dao;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

import br.dev.enzo.tarefas.factory.ArquivoTarefaFactory;
import br.dev.enzo.tarefas.model.Funcionario;
import br.dev.enzo.tarefas.model.Tarefa;

public class TarefaDAO {
	
	private Tarefa tarefa;
	private ArquivoTarefaFactory atf = new ArquivoTarefaFactory();
	
	public TarefaDAO(Tarefa tarefa){
		this.tarefa = tarefa;
	}
	
	
	public boolean gravar() {
		
		try {
			BufferedWriter bw = atf.getBw();
			bw.write(tarefa.toString());
			bw.flush();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	
	}
	
	public java.util.List<Tarefa> getTarefas(){
		
		java.util.List<Tarefa> tarefas = new ArrayList<Tarefa>();
		
		
		try {
			BufferedReader br;
			
			br = atf.getBr();
			
			String linha = "";
			while(linha != null) {
				
				linha = br.readLine();
				if(linha != null) {
					String[] tarefaVetor = linha.split(",");
					System.out.println(linha);
					Tarefa tarefa = new Tarefa(null);
					tarefa.setCodigo(tarefaVetor[0]);
					tarefa.setNome(tarefaVetor[1]);
					tarefa.setDescricao(tarefaVetor[2]);
					
					Date dataIncio = Date.valueOf(tarefa.getDataInicio());
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					String stringDataInicio = formato.format(dataIncio); 
					
					
					
					
					
					
					
					
				}
			}
			return tarefas;
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	
	}
	
	
	
	
	
}
