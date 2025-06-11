package br.dev.enzo.tarefas.model;

import java.time.LocalDate;

import br.dev.enzo.tarefas.utils.Utils;

public class Tarefa {

	private String nome;
	private String codigo;
	private String descricao;
	private Funcionario responsavel;
	private LocalDate dataInicio;
	private int prazo;
	private LocalDate dataEntrega;
	private Status status;
	
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

	public void setResponsavel(Funcionario responsavel) {
		this.responsavel = responsavel;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
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

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
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


}
