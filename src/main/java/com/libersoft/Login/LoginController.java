package com.libersoft.Login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libersoft.DAO.AdministradorDAO;
import com.libersoft.DAO.AlunoDAO;
import com.libersoft.DAO.BibliotecarioDAO;
import com.libersoft.Model.Administrador;
import com.libersoft.Model.Aluno;
import com.libersoft.Model.Bibliotecario;

@Controller
public class LoginController {
	@Autowired
	private BibliotecarioDAO bibliotecarioDAO;

	@Autowired
	private AlunoDAO alunoDAO;

	@Autowired
	private AdministradorDAO admDAO;

	@GetMapping("/index")
	public String indexUrl(HttpSession session) {
		
		Object verificarSessao = session.getAttribute("admLogado");
		if (verificarSessao != null) {
			session.invalidate();
		} else {
			verificarSessao = session.getAttribute("alunoLogado");
			if (verificarSessao != null) {
				session.invalidate();
			} else {
				verificarSessao = session.getAttribute("bibliotecarioLogado");
				if (verificarSessao != null) {
					session.invalidate();
				}
			}
		}
		
		return "login";
	}

	@GetMapping("/")
	public String exibirLogin(HttpSession session) {
		
		Object verificarSessao = session.getAttribute("admLogado");
		if (verificarSessao != null) {
			session.invalidate();
		} else {
			verificarSessao = session.getAttribute("alunoLogado");
			if (verificarSessao != null) {
				session.invalidate();
			} else {
				verificarSessao = session.getAttribute("bibliotecarioLogado");
				if (verificarSessao != null) {
					session.invalidate();
				}
			}
		}
		
		return "login";
	}

	@PostMapping("/")
	public String fazerLogin(String cpf, String senha, RedirectAttributes ra, HttpSession session) {
		// RETIRAR MASK
		cpf = cpf.replace(".", "").replace("-", "").replace("_", "");

		Aluno alunoLogado = this.alunoDAO.findByLoginAndSenha(cpf, senha);

		if (alunoLogado != null) {
			
			Aluno aluno = this.alunoDAO.findByCpf(cpf);
			session.setAttribute("alunoLogado", aluno);
			return "redirect:/aluno/home";
			
		} else {
			
			Bibliotecario bibliotecarioLogado = this.bibliotecarioDAO.findByLoginAndSenha(cpf, senha);
			
			if (bibliotecarioLogado == null) {
				ra.addFlashAttribute("mensagem", "CPF ou senha inválido(s).");
				return "redirect:/";
			} else {
				
				Bibliotecario bibliotecario = this.bibliotecarioDAO.findByCpf(cpf);
				session.setAttribute("bibliotecarioLogado", bibliotecario);
				return "redirect:/bibliotecario/home";
				
			}
			
		}
	}

	@GetMapping("/loginAdm")
	public String exibirLoginAdm(HttpSession session) {

		Object verificarSessao = session.getAttribute("admLogado");
		if (verificarSessao != null) {
			session.invalidate();
		} else {
			verificarSessao = session.getAttribute("alunoLogado");
			if (verificarSessao != null) {
				session.invalidate();
			} else {
				verificarSessao = session.getAttribute("bibliotecarioLogado");
				if (verificarSessao != null) {
					session.invalidate();
				}
			}
		}
		
		return "loginAdm";
	}

	@PostMapping("/loginAdm")
	public String fazerLoginAdm(String codigoDeAcesso, String senha, RedirectAttributes ra, HttpSession session) {
		Administrador admLogado = this.admDAO.findByUsuarioAndSenha(codigoDeAcesso, senha);

		if (admLogado == null) {
			ra.addFlashAttribute("mensagem", "Código de acesso ou senha inválido(s).");
			return "redirect:/loginAdm";
		} else {
			session.setAttribute("admLogado", admLogado);
			return "redirect:/adm/homeAdm";
		}
	}

	@GetMapping("/sair")
	public String sair(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/acessoNegado")
	public String acessoNegado() {
		return "acessoNegado";
	}
}