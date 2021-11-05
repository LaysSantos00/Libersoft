package com.libersoft.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.libersoft.DAO.AlunoDAO;
import com.libersoft.Model.Aluno;

@Controller
public class AlunoController {
	@Autowired
	private AlunoDAO alunoDAO;

	@ModelAttribute("alunos")
	public List<Aluno> getLista() {
		return this.alunoDAO.findAll();
	}

	@GetMapping("/bibliotecario/listarAlunos")
	public String listarAlunos() {
		return "listarAlunos";
	}
	
	@GetMapping("/css/cadastro.css")
	public String cadastroCss() {
		return "/css/cadastro.css";
	}
	
	@GetMapping("/bibliotecario/exibirFormAluno")
	public String exibirFormAluno(Aluno aluno) {
		return "formAluno";
	}

	@PostMapping("/bibliotecario/cadastrarAluno")
	public String cadastrarAluno(Aluno aluno) {
		System.out.println(aluno.getNome());
		this.alunoDAO.save(aluno);
		return "listarAlunos";
	}

	@GetMapping("/bibliotecario/editarAluno")
	public String editarAluno(Integer idAluno, Model model) {
		Aluno alu = this.alunoDAO.getById(idAluno);
		model.addAttribute("aluno", alu);
		return "formAluno";
	}

	@GetMapping("/bibliotecario/excluirAluno")
	public String excluirAluno(Integer idAluno) {
		this.alunoDAO.deleteById(idAluno);
		return "listarAlunos";
	}

}
