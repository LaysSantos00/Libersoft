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
	@ManyToOne
	@JoinColumn(name = "idLivro")
	private Livro idLivro;
	@ManyToOne
	@JoinColumn(name = "idAluno")
	private Aluno idAluno;
	@ManyToOne
	@JoinColumn(name = "idBibliotecario")
	private Bibliotecario idBibliotecario;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataEmprestimo;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataRenovacao;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDevolucao;
	@Column(length = 20, nullable = false)
	private String situacao;

	public Emprestimo() {
		
	}
	
	public Emprestimo(Integer idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public Emprestimo(Integer idEmprestimo, Livro idLivro, Aluno idAluno, Bibliotecario idBibliotecario,
			Date dataEmprestimo, Date dataRenovacao, Date dataDevolucao) {
		super();
		this.idEmprestimo = idEmprestimo;
		this.idLivro = idLivro;
		this.idAluno = idAluno;
		this.idBibliotecario = idBibliotecario;
		this.dataEmprestimo = dataEmprestimo;
		this.dataRenovacao = dataRenovacao;
		this.dataDevolucao = dataDevolucao;
	}

	public Integer getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(Integer idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public Livro getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Livro idLivro) {
		this.idLivro = idLivro;
	}

	public Aluno getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Aluno idAluno) {
		this.idAluno = idAluno;
	}

	public Bibliotecario getIdBibliotecario() {
		return idBibliotecario;
	}

	public void setIdBibliotecario(Bibliotecario idBibliotecario) {
		this.idBibliotecario = idBibliotecario;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataRenovacao() {
		return dataRenovacao;
	}

	public void setDataRenovacao(Date dataRenovacao) {
		this.dataRenovacao = dataRenovacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
}