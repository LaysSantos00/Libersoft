package com.libersoft.Login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libersoft.DAO.BibliotecarioDAO;
import com.libersoft.DAO.LivroDAO;
import com.libersoft.Model.Bibliotecario;


@Controller
public class LoginController {
	@Autowired
	private BibliotecarioDAO bibliotecarioDAO;
	@Autowired
	private LivroDAO livroDAO;

	@GetMapping("/login")
	public String exibirLogin() {
		return "login";
	}

	@PostMapping("/fazerLogin")
	public String fazerLogin(String cpf, String senha, RedirectAttributes ra, HttpSession session) {
		Bibliotecario bibliotecarioLogado = this.bibliotecarioDAO.findByLoginAndSenha(cpf, senha);
		if (bibliotecarioLogado == null) {
			ra.addFlashAttribute("mensagem", "Cpf ou senha inválidos");
			return "redirect:/";
		} else {
			session.setAttribute("bibliotecarioLogado", bibliotecarioLogado);
			return "redirect:/bibliotecario/listarLivros";
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