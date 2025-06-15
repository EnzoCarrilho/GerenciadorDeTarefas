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
import br.dev.enzo.tarefas.utils.Utils;

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
					Tarefa tarefa = new Tarefa(null);
					tarefa.setCodigo(tarefaVetor[0]);
					tarefa.setNome(tarefaVetor[1]);
					tarefa.setDescricao(tarefaVetor[2]);
					tarefa.setNomeResponsavel(tarefaVetor[3]);
					tarefa.setDataInicio(tarefaVetor[4]);
					//tarefa.setPrazo(Integer.parseInt(tarefaVetor[5]));
					tarefa.setDataEntrega(tarefaVetor[5]);	
					tarefas.add(tarefa);
				}
			}
			return tarefas;
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	
	}
	
	
	
	
	
}
