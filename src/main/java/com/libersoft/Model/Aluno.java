package com.libersoft.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAluno;
	
	@Size(min=7, max=100)
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Email
	@Column(length = 100, nullable = false)
	private String email;
	
	@Size(min=8, max=100)
	@Column(length = 20, nullable = false)
	private String senha;
	
	@Column(length = 11, nullable = false)
	private String telefone;
	
	@Size(min=8, max=70)
	@Column(length = 70, nullable = false)
	private String endereco;

	@CPF
	@Column(length = 11, nullable = false)
	private String cpf;

	public Aluno() {

	}

	public Aluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public Aluno(Integer idAluno, String nome, String email, String senha, String telefone, String endereco,
			String cpf) {
		super();
		this.idAluno = idAluno;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cpf = cpf;
	}

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
