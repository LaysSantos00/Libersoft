package com.libersoft.Model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Emprestimo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmprestimo;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataEmprestimo;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDevolucao;
	
	@Column(length = 1, nullable = false)
	private Integer renovacoes;
	
	@ManyToOne
	@JoinColumn(name = "idAluno")
	private Aluno idAluno;
	
	@ManyToOne
	@JoinColumn(name = "idLivro")
	private Livro idLivro;
	
	@Column(length = 20, nullable = false)
	private String situacao;

	public Emprestimo() {
		
	}
	
	public Emprestimo(Integer idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public Emprestimo(Integer idEmprestimo, Date dataEmprestimo, Date dataDevolucao, 
			Integer renovacoes, Aluno idAluno, Livro idLivro, String situacao) {
		super();
		this.idEmprestimo = idEmprestimo;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.renovacoes = renovacoes;
		this.idAluno = idAluno;
		this.idLivro = idLivro;
		this.situacao = situacao;
	}

	public Integer getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(Integer idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Integer getRenovacoes() {
		return renovacoes;
	}

	public void setRenovacoes(Integer renovacoes) {
		this.renovacoes = renovacoes;
	}

	public Aluno getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Aluno idAluno) {
		this.idAluno = idAluno;
	}

	public Livro getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Livro idLivro) {
		this.idLivro = idLivro;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
}