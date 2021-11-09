package com.libersoft.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class Bibliotecario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBibliotecario;
	
	@Size(min=7, max=100)
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Email
	@Size(min=7, max=100)
	@Column(length = 50, nullable = false)
	private String email;
	
	@Size(min=8, max=100)
	@Column(length = 20, nullable = false)
	private String senha;
	
	@Column(length = 11, nullable = false)
	private String telefone;
	
	@Column(length = 11, nullable = false)
	private String cpf;

	public Bibliotecario() {

	}

	public Bibliotecario(Integer idBibliotecario) {
		this.setIdBibliotecario(idBibliotecario);
	}

	public Bibliotecario(Integer idBibliotecario, String nome, String email, String senha, String telefone,
			String cpf) {
		super();
		this.idBibliotecario = idBibliotecario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.cpf = cpf;
	}

	public Integer getIdBibliotecario() {
		return idBibliotecario;
	}

	public void setIdBibliotecario(Integer idBibliotecario) {
		this.idBibliotecario = idBibliotecario;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
