package com.libersoft.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Administrador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAdm;

	@Size(min=6, max=20)
	@Column(length = 20, nullable = false)
	private String usuario;
	
	@Size(min=8, max=20)
	@Column(length = 20, nullable = false)
	private String senha;
	
	public Administrador() {
		
	}
	public Administrador(Integer idAdm) {
		this.idAdm = idAdm;
	}
	public Administrador(Integer idAdm, String usuario, String senha) {
		this.idAdm = idAdm;
		this.usuario = usuario;
		this.senha = senha;
	}
	public Integer getIdAdm() {
		return idAdm;
	}
	public void setIdAdm(Integer idAdm) {
		this.idAdm = idAdm;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}