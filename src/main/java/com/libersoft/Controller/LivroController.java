package com.libersoft.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.libersoft.DAO.LivroDAO;
import com.libersoft.Model.Livro;

@Controller
public class LivroController {
	@Autowired
	private LivroDAO livroDAO;

	@ModelAttribute("livros")
	public List<Livro> getLista() {
		return this.livroDAO.findAll();
	}

	@GetMapping("/bibliotecario/listarLivros")
	public String listarLivros() {
		return "listarLivros";
	}
	
	@GetMapping("/bibliotecario/exibirFormLivro")
	public String exibirFormLivro(Livro livro) {
		return "formLivro";
	}

	@PostMapping("/bibliotecario/cadastrarLivro")
	public String cadastrarLivro(Livro livro) {
		System.out.println(livro.getTitulo());
		this.livroDAO.save(livro);
		return "listarLivros";
	}

	@GetMapping("/bibliotecario/editarLivro")
	public String editarLivro(Integer idLivro, Model model) {
		Livro liv = this.livroDAO.getById(idLivro);
		model.addAttribute("livro", liv);
		return "formLivro";
	}

	@GetMapping("/bibliotecario/excluirLivro")
	public String excluirLivro(Integer idLivro) {
		this.livroDAO.deleteById(idLivro);
		return "listarLivros";
	}

}
