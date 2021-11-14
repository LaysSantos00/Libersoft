package com.libersoft.Login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libersoft.DAO.AlunoDAO;
import com.libersoft.DAO.BibliotecarioDAO;
import com.libersoft.DAO.LivroDAO;
import com.libersoft.Model.Aluno;
import com.libersoft.Model.Bibliotecario;


@Controller
public class LoginController {
	@Autowired
	private BibliotecarioDAO bibliotecarioDAO;
	
	@Autowired
	private AlunoDAO alunoDAO;
	
	@GetMapping("/index")
	public String indexUrl() {
		return "login";
	}
	
	@GetMapping("/")
	public String exibirLogin() {
		return "login";
	}

	@PostMapping("/")
	public String fazerLogin(String cpf, String senha, RedirectAttributes ra, HttpSession session) {
		// RETIRAR MASK
		cpf = cpf.replace(".", "").replace("-", "").replace("_", "");
		
		Aluno alunoLogado = this.alunoDAO.findByLoginAndSenha(cpf, senha);
		
		if (alunoLogado == null) {
			Bibliotecario bibliotecarioLogado = this.bibliotecarioDAO.findByLoginAndSenha(cpf, senha);
			if (bibliotecarioLogado == null) {
				ra.addFlashAttribute("mensagem", "CPF ou senha inválido(s).");
				return "redirect:/";
			} else {
				session.setAttribute("bibliotecarioLogado", bibliotecarioLogado);
				return "redirect:/bibliotecario/home";
			}
		} else {
			session.setAttribute("alunoLogado", alunoLogado);
			return "redirect:/aluno/home";
		}
	}

	@GetMapping("/sair")
	public String sair(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/acessoNegado")
	public String acessoNegado() {
		return "acesso_negado";
	}
}