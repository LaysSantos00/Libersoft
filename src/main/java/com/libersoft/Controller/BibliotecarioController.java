package com.libersoft.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.libersoft.DAO.BibliotecarioDAO;
import com.libersoft.Model.Bibliotecario;

@Controller
public class BibliotecarioController {
	@Autowired
	private BibliotecarioDAO bibliotecarioDAO;

	@ModelAttribute("bibliotecarios")
	public List<Bibliotecario> getLista() {
		return this.bibliotecarioDAO.findAll();
	}

	@GetMapping("/adm/listarBibliotecarios")
	public String listarBibliotecarios() {
		return "listarBibliotecarios";
	}

	@GetMapping("/adm/cadastroBibliotecario")
	public String exibirFormBibliotecario(Bibliotecario bibliotecario) {
		return "formBibliotecario";
	}

	@PostMapping("/adm/cadastroBibliotecario")
	public String cadastrarBibliotecario(Bibliotecario bibliotecario) {
		System.out.println(bibliotecario.getNome());
		this.bibliotecarioDAO.save(bibliotecario);
		return "listarBibliotecarios";
	}

	@GetMapping("/adm/editarBibliotecario")
	public String editarBibliotecario(Integer idBibliotecario, Model model) {
		Bibliotecario bib = this.bibliotecarioDAO.getById(idBibliotecario);
		model.addAttribute("bibliotecario", bib);
		return "formBibliotecario";
	}

	@GetMapping("/adm/excluirBibliotecario")
	public String excluirBibliotecario(Integer idBibliotecario) {
		this.bibliotecarioDAO.deleteById(idBibliotecario);
		return "listarBibliotecario";
	}
}