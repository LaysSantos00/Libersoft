package com.libersoft.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	@GetMapping("/bibliotecario/cadastroLivro")
	public String exibirFormLivro(Livro livro) {
		return "cadastroLivro";
	}

	@PostMapping("/bibliotecario/cadastroLivro")
	public String cadastrarLivro(@Valid Livro livro, BindingResult result) {
		
		if (result.hasErrors()) {
			return "cadastroLivro";
		}
		
		this.livroDAO.save(livro);
		return "redirect:listarLivros";
		
	}

	@GetMapping("/bibliotecario/editarLivro")
	public String getEditarLivro(Integer idLivro, Model model) {
		Livro liv = this.livroDAO.getById(idLivro);
		model.addAttribute("livro", liv);
		
		return "editarLivro";
	}
	
	@PostMapping("/bibliotecario/editarLivro")
	public String postEditarLivro(@Valid Livro livro, BindingResult result) {
		
		if (result.hasErrors()) {
			return "editarLivro";
		}
		
		this.livroDAO.save(livro);
		return "redirect:listarLivros";
		
	}

	@GetMapping("/bibliotecario/excluirLivro")
	public String excluirLivro(Integer idLivro) {
		if (!(idLivro == null)) {
			this.livroDAO.deleteById(idLivro);
		}
		return "redirect:listarLivros";
	}

}
