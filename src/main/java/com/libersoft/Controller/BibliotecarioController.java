package com.libersoft.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.libersoft.DAO.BibliotecarioDAO;
import com.libersoft.Model.Bibliotecario;
import com.libersoft.Service.BibliotecarioValidationService;

@Controller
public class BibliotecarioController {
	String oldEmail = "";
	String oldCpf = "";

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
		return "cadastroBibliotecario";
	}

	@PostMapping("/adm/cadastroBibliotecario")
	public String cadastrarBibliotecario(@Valid Bibliotecario bibliotecario, BindingResult result) {

		/*
		 * FAZENDO A LIGAÇÃO COM O SERVICE DE VALIDAÇÃO DOS CAMPOS DO USUÁRIO
		 */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.libersoft.Service");
		context.refresh();
		BibliotecarioValidationService bibliotecarioService = context.getBean(BibliotecarioValidationService.class);
		context.close();

		/*
		 * FORMATANDO AS STRINGS DE TELEFONE E CPF PARA FICAR APENAS NÚMEROS DENTRO DA
		 * STRING
		 */
		bibliotecario.setTelefone(
				bibliotecario.getTelefone().replace("(", "").replace(")", "").replace(" ", "").replace("_", ""));
		bibliotecario.setCpf(bibliotecario.getCpf().replace(".", "").replace("-", "").replace("_", ""));

		String erros = bibliotecarioService.validarBibliotecario(bibliotecario);

		/*
		 * CONFERE SE OS CAMPOS DO TIPO ÚNICO JÁ EXISTEM CADASTRADOS NO BANCO DE DADOS
		 */
		if (bibliotecarioDAO.existsByEmail(bibliotecario.getEmail())) {
			erros += "email$bibliotecario$email já cadastrado$";
		}
		if (bibliotecarioDAO.existsByCpf(bibliotecario.getCpf())) {
			erros += "cpf$bibliotecario$CPF já cadastrado$";
		}

		/*
		 * TRATANDO OS ERROS PARA REGISTRAR ELES NO OBJETO 'RESULT', QUE ARMAZENA TODOS
		 * OS ERROS DOS CAMPOS DE CADASTRO
		 */
		if (!erros.isEmpty()) {
			System.out.println(erros);
			String[] listaErros = erros.split("\\$");
			for (int i = 0; i < listaErros.length; i += 3) {
				result.rejectValue(listaErros[i], listaErros[i + 1], listaErros[i + 2]);
			}
		}

		if (result.hasErrors()) {
			return "cadastroBibliotecario";
		}

		this.bibliotecarioDAO.save(bibliotecario);
		return "redirect:listarBibliotecarios";
	}

	@GetMapping("/adm/editarBibliotecario")
	public String getEditarBibliotecario(Integer idBibliotecario, Model model) {
		Bibliotecario bib = this.bibliotecarioDAO.getById(idBibliotecario);
		model.addAttribute("bibliotecario", bib);

		oldEmail = bib.getEmail();
		oldCpf = bib.getCpf();

		return "editarBibliotecario";
	}

	@PostMapping("/adm/editarBibliotecario")
	public String postEditarBibliotecario(@Valid Bibliotecario bibliotecario, BindingResult result) {

		/*
		 * FAZENDO A LIGAÇÃO COM O SERVICE DE VALIDAÇÃO DOS CAMPOS DO USUÁRIO
		 */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.libersoft.Service");
		context.refresh();
		BibliotecarioValidationService bibliotecarioService = context.getBean(BibliotecarioValidationService.class);
		context.close();

		/*
		 * FORMATANDO AS STRINGS DE TELEFONE E CPF PARA FICAR APENAS NÚMEROS DENTRO DA
		 * STRING
		 */
		bibliotecario.setTelefone(
				bibliotecario.getTelefone().replace("(", "").replace(")", "").replace(" ", "").replace("_", ""));
		bibliotecario.setCpf(bibliotecario.getCpf().replace(".", "").replace("-", "").replace("_", ""));

		String erros = bibliotecarioService.validarBibliotecario(bibliotecario);

		String email = bibliotecario.getEmail();
		String cpf = bibliotecario.getCpf();

		/*
		 * VERIFICA SE O CAMPO DE EMAIL E CPF É IGUAL AO REGISTRO NO ESTADO PASSADO. SE
		 * NÃO FOR IGUAL, IRÁ VERIFICAR SE EXISTE OUTRO EMAIL OU CPF IGUAL PARA NÃO
		 * HAVER ERROS NO CADASTRO
		 */
		if (!oldEmail.equals(email)) {
			if (bibliotecarioDAO.existsByEmail(email)) {
				erros += "email$bibliotecario$email já cadastrado$";
			}
		}

		if (!oldCpf.equals(cpf)) {
			if (bibliotecarioDAO.existsByCpf(cpf)) {
				erros += "cpf$bibliotecario$CPF já cadastrado$";
			}
		}

		/*
		 * TRATANDO OS ERROS PARA REGISTRAR ELES NO OBJETO 'RESULT', QUE ARMAZENA TODOS
		 * OS ERROS DOS CAMPOS DE CADASTRO
		 */
		if (!erros.isEmpty()) {
			System.out.println(erros);
			String[] listaErros = erros.split("\\$");
			for (int i = 0; i < listaErros.length; i += 3) {
				result.rejectValue(listaErros[i], listaErros[i + 1], listaErros[i + 2]);
			}
		}

		if (result.hasErrors()) {
			return "editarBibliotecario";
		}

		this.bibliotecarioDAO.save(bibliotecario);
		return "redirect:listarBibliotecarios";
	}

	@GetMapping("/adm/excluirBibliotecario")
	public String excluirBibliotecario(Integer idBibliotecario) {
		if (!(idBibliotecario == null)) {
			this.bibliotecarioDAO.deleteById(idBibliotecario);
		}
		return "redirect:listarBibliotecarios";
	}

	// pagina para home do Bibliotecario
	@GetMapping("/bibliotecario/home")
	public String homeBibliotecario() {
		return "homeBibliotecario";
	}

	// pagina para home do adm
	@GetMapping("/adm/home")
	public String homeAdm() {
		return "homeAdm";
	}
	
	
	
	
	
}
