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
		
		// VALIDAÇÕES TESTE //
		if (
				aluno.getNome() == "" || 
				aluno.getEmail() == "" || 
				aluno.getSenha() == "" ||
				aluno.getTelefone() == "" || 
				aluno.getEndereco() == "" || 
				aluno.getCpf() == ""
				) {
			System.out.println("campo nulo");
			return "formAluno";
		} else if (
				aluno.getNome().length() < 3 ||
				aluno.getEmail().length() < 13 ||
				aluno.getSenha().length() < 8 ||
				aluno.getTelefone().length() < 10 ||
				aluno.getEndereco().length() < 8 ||
				aluno.getCpf().length() != 11
				) {
			System.out.println("tamanho pequeno");
			return "formAluno";
		} else if (
				aluno.getNome().length() > 100 ||
				aluno.getEmail().length() > 100 ||
				aluno.getSenha().length() > 20 ||
				aluno.getTelefone().length() > 11 ||
				aluno.getEndereco().length() > 70 ||
				aluno.getCpf().length() > 11
				) {
			System.out.println("tamanho grande");
			return "formAluno";
		} else {
			this.alunoDAO.save(aluno);
			return "listarAlunos";
		}
		// VALIDAÇÕES TESTE //
		
		/* SE NÃO FOR USAR ESSAS VALIDAÇÕES ACIMA, DESCOMENTAR ABAIXO E RETIRAR AS VALIDAÇÕES
		this.alunoDAO.save(aluno);
		return "listarAlunos"; 
		 */
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
