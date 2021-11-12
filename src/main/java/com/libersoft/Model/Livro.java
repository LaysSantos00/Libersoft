package com.libersoft.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLivro;
	
	@Size(min=1, max=50)
	@Column(length = 50, nullable = false)
	private String categoria;
	
	@Size(min=1, max=50)
	@Column(length = 50, nullable = false)
	private String titulo;
	
	private long isbn;

	@Min(1)
	@Max(100)
	private int volume;
	
	@Size(min=2, max=60)
	@Column(length = 60, nullable = false)
	private String autor;
	
	@Min(0)
	@Max(30000)
	private int quantidade;
	
	@Size(min=10, max=500)
	@Column(length = 500, nullable = false)
	private String resumo;
	
	@Size(min=3, max=100, message="escolha uma imagem")
	@Column(length = 100, nullable = false)
	private String imagem;

	public Livro() {

	}

	public Livro(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public Livro(Integer idLivro, String categoria, String titulo, int volume, String autor, int quantidade,
			String resumo, String imagem) {
		super();
		this.idLivro = idLivro;
		this.categoria = categoria;
		this.titulo = titulo;
		this.volume = volume;
		this.autor = autor;
		this.quantidade = quantidade;
		this.resumo = resumo;
		this.imagem = imagem;
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public void setId(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	
}
