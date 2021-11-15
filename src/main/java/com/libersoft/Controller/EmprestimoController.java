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

import com.libersoft.DAO.EmprestimoDAO;
import com.libersoft.Model.Aluno;
import com.libersoft.Model.Emprestimo;
import com.libersoft.Model.Livro;

@Controller
public class EmprestimoController {
	@Autowired
	private EmprestimoDAO emprestimoDAO;

	@ModelAttribute("emprestimos")
	public List<Emprestimo> getLista() {
		return this.emprestimoDAO.findAll();
	}

	@GetMapping("/bibliotecario/listarEmprestimos")
	public String listarEmprestimos() {
		return "listarEmprestimos";
	}
	
	@GetMapping("/bibliotecario/cadastroEmprestimo")
	public String exibirFormEmprestimo(Emprestimo emprestimo) {
		return "cadastroEmprestimo";
	}

	/*@PostMapping("/bibliotecario/cadastroEmprestimo")
	public String cadastrarEmprestimo(Aluno aluno, Livro livro, BindingResult result) {
		
		
		
		if (result.hasErrors()) {
			return "cadastroEmprestimo";
		}
		
		this.emprestimoDAO.save(emprestimo);
		return "redirect:listarEmprestimos";
		
	}*/

	@GetMapping("/bibliotecario/editarEmprestimo")
	public String getEditarEmprestimo(Integer idEmprestimo, Model model) {
		Emprestimo emp = this.emprestimoDAO.getById(idEmprestimo);
		model.addAttribute("emprestimo", emp);
		
		return "editarEmprestimo";
	}
	
	@PostMapping("/bibliotecario/editarEmprestimo")
	public String postEditarEmprestimo(@Valid Emprestimo emprestimo, BindingResult result) {
		
		if (result.hasErrors()) {
			return "editarEmprestimo";
		}
		
		this.emprestimoDAO.save(emprestimo);
		return "redirect:listarEmprestimos";
		
	}

	@GetMapping("/bibliotecario/excluirEmprestimo")
	public String excluirEmprestimo(Integer idEmprestimo) {
		if (!(idEmprestimo == null)) {
			this.emprestimoDAO.deleteById(idEmprestimo);
		}
		return "redirect:listarEmprestimos";
	}

}
