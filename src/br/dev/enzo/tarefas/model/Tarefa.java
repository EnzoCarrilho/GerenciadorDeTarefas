package br.dev.enzo.tarefas.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.dev.enzo.tarefas.dao.FuncionarioDAO;
import br.dev.enzo.tarefas.utils.Utils;

public class Tarefa {

	private String nome;
	private String codigo;
	private String descricao;
	private Funcionario responsavel;
	private String nomeResponsavel;
	private LocalDate dataInicio;
	private int prazo;
	private LocalDate dataEntrega;
	private Status status;
	private String txtDataInicio;
	private String txtDataEntrega;
	
	public Tarefa(Funcionario responsavel) {
		setCodigo(Utils.gerarUUID8());
		this.responsavel = responsavel;
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Funcionario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(int index) {
		
		FuncionarioDAO dao = new FuncionarioDAO(null);
		List<Funcionario> funcionarios = dao.getFuncionarios();
		
		responsavel = funcionarios.get(index);
		nomeResponsavel = funcionarios.get(index).getNome();
		
	}
	
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}
	
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataTxt) {
		this.dataInicio = LocalDate.parse(dataTxt, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		txtDataInicio = dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public LocalDate getDataPrevistaEntrega() {
		return dataInicio.plusDays(prazo);
		
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataTxt) {
		this.dataEntrega = LocalDate.parse(dataTxt, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		txtDataEntrega = dataEntrega.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
	}

	public Status getStatus() {
		
		LocalDate hoje = LocalDate.now();
		
		if(hoje.isBefore(dataInicio)) {
			
			status = Status.NAO_INICIADO;
		}else if(hoje.equals(dataInicio) && hoje.isBefore(dataEntrega)){
			
			status = Status.EM_ANDAMENTO;
		}else if(hoje.isBefore(dataEntrega)) {
			status = Status.EM_ATRASO;
		}else {
			status = Status.CONCLUIDO;
		}
		
		return status;
	}
	
	@Override
	public String toString() {
		return codigo + "," + nome + "," + descricao + "," + nomeResponsavel + "," + txtDataInicio + "," + txtDataEntrega + "," + getStatus() + "\n";
	}



}
