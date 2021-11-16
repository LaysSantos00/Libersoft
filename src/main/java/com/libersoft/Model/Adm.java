package com.libersoft.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Adm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAdm;

	@Size(min=6, max=6)
	@Column(length = 6, nullable = false)
	private String codigoDeAcesso;
	
	@Size(min=8, max=20)
	@Column(length = 20, nullable = false)
	private String senha;
	
	public Adm() {
		
	}
	public Adm(Integer idAdm) {
		this.idAdm = idAdm;
	}
	public Adm(Integer idAdm, String codigoDeAcesso, String senha) {
		this.idAdm = idAdm;
		this.codigoDeAcesso = codigoDeAcesso;
		this.senha = senha;
	}
	public Integer getIdAdm() {
		return idAdm;
	}
	public void setIdAdm(Integer idAdm) {
		this.idAdm = idAdm;
	}
	public String getCodigoDeAcesso() {
		return codigoDeAcesso;
	}
	public void setCodigoDeAcesso(String codigoDeAcesso) {
		this.codigoDeAcesso = codigoDeAcesso;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}