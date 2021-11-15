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

import com.libersoft.DAO.AlunoDAO;
import com.libersoft.DAO.BibliotecarioDAO;
import com.libersoft.Model.Aluno;
import com.libersoft.Model.Bibliotecario;
import com.libersoft.Service.AlunoValidationService;
import com.libersoft.Service.BibliotecarioValidationService;

@Controller
public class BibliotecarioController {
	String oldEmail = "";
	String oldCpf = "";
	
	@Autowired
	private BibliotecarioDAO bibliotecarioDAO;
	
	@Autowired
	private AlunoDAO alunoDAO;

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
		
		/* FAZENDO A LIGAÇÃO COM O SERVICE DE VALIDAÇÃO
		 * DOS CAMPOS DO USUÁRIO */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.libersoft.Service");
		context.refresh();
		BibliotecarioValidationService bibliotecarioService = context.getBean(BibliotecarioValidationService.class);
		context.close();
		
		
		/* FORMATANDO AS STRINGS DE TELEFONE E CPF
		 * PARA FICAR APENAS NÚMEROS DENTRO DA STRING */
		bibliotecario.setTelefone(bibliotecario.getTelefone().replace("(", "").replace(")", "").replace(" ", "").replace("_", ""));
		bibliotecario.setCpf(bibliotecario.getCpf().replace(".", "").replace("-", "").replace("_", ""));
		
		String erros = bibliotecarioService.validarBibliotecario(bibliotecario);
		
		/* CONFERE SE OS CAMPOS DO TIPO ÚNICO JÁ
		 * EXISTEM CADASTRADOS NO BANCO DE DADOS */
		if (bibliotecarioDAO.existsByEmail(bibliotecario.getEmail())) {
			erros += "email$bibliotecario$email já cadastrado$";
		}
		if (bibliotecarioDAO.existsByCpf(bibliotecario.getCpf())) {
			erros += "cpf$bibliotecario$CPF já cadastrado$";	
		}
		
		/* TRATANDO OS ERROS PARA REGISTRAR ELES NO
		 * OBJETO 'RESULT', QUE ARMAZENA TODOS OS ERROS
		 * DOS CAMPOS DE CADASTRO */
		if (!erros.isEmpty() ) {			
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
		
		/* FAZENDO A LIGAÇÃO COM O SERVICE DE VALIDAÇÃO
		 * DOS CAMPOS DO USUÁRIO */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.libersoft.Service");
		context.refresh();
		BibliotecarioValidationService bibliotecarioService = context.getBean(BibliotecarioValidationService.class);
		context.close();
		
		
		/* FORMATANDO AS STRINGS DE TELEFONE E CPF
		 * PARA FICAR APENAS NÚMEROS DENTRO DA STRING */
		bibliotecario.setTelefone(bibliotecario.getTelefone().replace("(", "").replace(")", "").replace(" ", "").replace("_", ""));
		bibliotecario.setCpf(bibliotecario.getCpf().replace(".", "").replace("-", "").replace("_", ""));
		
		String erros = bibliotecarioService.validarBibliotecario(bibliotecario);
		
		String email = bibliotecario.getEmail();
		String cpf = bibliotecario.getCpf();
		
		/* VERIFICA SE O CAMPO DE EMAIL E CPF
		 * É IGUAL AO REGISTRO NO ESTADO PASSADO.
		 * SE NÃO FOR IGUAL, IRÁ VERIFICAR SE EXISTE
		 * OUTRO EMAIL OU CPF IGUAL PARA NÃO
		 * HAVER ERROS NO CADASTRO */
		if(!oldEmail.equals(email)) {
			if (bibliotecarioDAO.existsByEmail(email)) {
				erros += "email$bibliotecario$email já cadastrado$";
			}
		}
		
		if (!oldCpf.equals(cpf)) {
			if (bibliotecarioDAO.existsByCpf(cpf)) {
				erros += "cpf$bibliotecario$CPF já cadastrado$";	
			}
		}
		
		/* TRATANDO OS ERROS PARA REGISTRAR ELES NO
		 * OBJETO 'RESULT', QUE ARMAZENA TODOS OS ERROS
		 * DOS CAMPOS DE CADASTRO */
		if (!erros.isEmpty() ) {			
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
	
	//home para adm
	@GetMapping("/adm/home")
	public String homeAdm() {
		return "homeAdm";
	}
	
	// tudo relacionado ao ALUNO
	@GetMapping("/adm/listarAlunosAdm")
	public String listarAlunosAdm() {
		return "listarAlunosAdm";
	}
	
	@GetMapping("/adm/cadastroAlunoAdm")
	public String exibirFormAluno(Aluno aluno) {
		return "cadAlunoAdm";
	}
	
	@PostMapping("/adm/cadastroAlunoAdm")
	public String cadastrarAluno(@Valid Aluno aluno, BindingResult result) {
		
		/* FAZENDO A LIGAÇÃO COM O SERVICE DE VALIDAÇÃO
		 * DOS CAMPOS DO USUÁRIO */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.libersoft.Service");
		context.refresh();
		AlunoValidationService alunoService = context.getBean(AlunoValidationService.class);
		context.close();
		
		/* FORMATANDO AS STRINGS DE TELEFONE E CPF
		 * PARA FICAR APENAS NÚMEROS DENTRO DA STRING */
		aluno.setTelefone(aluno.getTelefone().replace("(", "").replace(")", "").replace(" ", "").replace("_", ""));
		aluno.setCpf(aluno.getCpf().replace(".", "").replace("-", "").replace("_", ""));
		
		String erros = alunoService.validarAluno(aluno);
		
		/* CONFERE SE OS CAMPOS DO TIPO ÚNICO JÁ
		 * EXISTEM CADASTRADOS NO BANCO DE DADOS */
		if (alunoDAO.existsByEmail(aluno.getEmail())) {
			erros += "email$aluno$email já cadastrado$";
		}
		if (alunoDAO.existsByCpf(aluno.getCpf())) {
			erros += "cpf$aluno$CPF já cadastrado$";	
		}
		
		/* TRATANDO OS ERROS PARA REGISTRAR ELES NO
		 * OBJETO 'RESULT', QUE ARMAZENA TODOS OS ERROS
		 * DOS CAMPOS DE CADASTRO */
		if (!erros.isEmpty() ) {
			String[] listaErros = erros.split("\\$");
			for (int i = 0; i < listaErros.length; i += 3) {
				result.rejectValue(listaErros[i], listaErros[i + 1], listaErros[i + 2]);
			}
		}
		
		if (result.hasErrors()){
			return "cadAlunoAdm";
		}
		
		this.alunoDAO.save(aluno);
		return "redirect:listarAlunosAdm";
	}
	
	@GetMapping("/adm/editarAlunoAdm")
	public String getEditarAluno(Integer idAluno, Model model) {
		Aluno alu = this.alunoDAO.getById(idAluno);
		model.addAttribute("aluno", alu);
		
		oldEmail = alu.getEmail();
		oldCpf = alu.getCpf();
		
		return "editarAlunoAdm";
	}
	
	@PostMapping("/adm/editarAlunoAdm")
	public String postEditarAluno(@Valid Aluno aluno, BindingResult result) {
		
		/* FAZENDO A LIGAÇÃO COM O SERVICE DE VALIDAÇÃO
		 * DOS CAMPOS DO USUÁRIO */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.libersoft.Service");
		context.refresh();
		AlunoValidationService alunoService = context.getBean(AlunoValidationService.class);
		context.close();
		
		/* FORMATANDO AS STRINGS DE TELEFONE E CPF
		 * PARA FICAR APENAS NÚMEROS DENTRO DA STRING */
		aluno.setTelefone(aluno.getTelefone().replace("(", "").replace(")", "").replace(" ", "").replace("_", ""));
		aluno.setCpf(aluno.getCpf().replace(".", "").replace("-", "").replace("_", ""));
		
		String erros = alunoService.validarAluno(aluno);
		
		String email = aluno.getEmail();
		String cpf = aluno.getCpf();
		
		/* VERIFICA SE O CAMPO DE EMAIL E CPF
		 * É IGUAL AO REGISTRO NO ESTADO PASSADO.
		 * SE NÃO FOR IGUAL, IRÁ VERIFICAR SE EXISTE
		 * OUTRO EMAIL OU CPF IGUAL PARA NÃO
		 * HAVER ERROS NO CADASTRO */
		if(!oldEmail.equals(email)) {
			if (alunoDAO.existsByEmail(email)) {
				erros += "email$aluno$email já cadastrado$";
			}
		}
		
		if (!oldCpf.equals(cpf)) {
			if (alunoDAO.existsByCpf(cpf)) {
				erros += "cpf$aluno$CPF já cadastrado$";	
			}
		}
		
		
		/* TRATANDO OS ERROS PARA REGISTRAR ELES NO
		 * OBJETO 'RESULT', QUE ARMAZENA TODOS OS ERROS
		 * DOS CAMPOS DE CADASTRO */
		if (!erros.isEmpty()) {			
			System.out.println(erros);
			String[] listaErros = erros.split("\\$");
			for (int i = 0; i < listaErros.length; i += 3) {
				result.rejectValue(listaErros[i], listaErros[i + 1], listaErros[i + 2]);
			}
		}
		
		if (result.hasErrors()){
			return "editarAlunoAdm";
		}
		
		this.alunoDAO.save(aluno);
		return "redirect:listarAlunosAdm";
	}
	
	@GetMapping("/adm/excluirAlunoAdm")
	public String excluirAluno(Integer idAluno) {
		if (!(idAluno == null)) {
			this.alunoDAO.deleteById(idAluno);	
		}
		return "redirect:listarAlunosAdm";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

