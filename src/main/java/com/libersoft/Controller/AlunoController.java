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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libersoft.DAO.AlunoDAO;
import com.libersoft.DAO.BibliotecarioDAO;
import com.libersoft.DAO.EmprestimoDAO;
import com.libersoft.Model.Aluno;
import com.libersoft.Service.AlunoValidationService;

@Controller
public class AlunoController {
	String oldEmail = "";
	String oldCpf = "";
	
	@Autowired
	private AlunoDAO alunoDAO;
	
	@Autowired
	private BibliotecarioDAO bibliotecarioDAO;
	
	@Autowired
	private EmprestimoDAO emprestimoDAO;

	@ModelAttribute("alunos")
	public List<Aluno> getLista() {
		return this.alunoDAO.findAll();
	}
	
	@GetMapping("/bibliotecario/home")
	public String homeBibliotecario() {
		return "homeBibliotecario";
	}

	@GetMapping("/bibliotecario/listarAlunos")
	public String listarAlunos() {
		return "listarAlunos";
	}
	
	@GetMapping("/bibliotecario/cadastroAluno")
	public String exibirFormAluno(Aluno aluno) {
		return "cadastroAluno";
	}

	@PostMapping("/bibliotecario/cadastroAluno")
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
		if (alunoDAO.existsByEmail(aluno.getEmail()) || bibliotecarioDAO.existsByEmail(aluno.getEmail())) {
			erros += "email$aluno$email já cadastrado$";
		}
		if (alunoDAO.existsByCpf(aluno.getCpf()) || bibliotecarioDAO.existsByCpf(aluno.getCpf())) {
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
			return "cadastroAluno";
		}
		
		this.alunoDAO.save(aluno);
		return "redirect:listarAlunos";
	}

	@GetMapping("/bibliotecario/editarAluno")
	public String getEditarAluno(Integer idAluno, Model model) {
		Aluno alu = this.alunoDAO.getById(idAluno);
		model.addAttribute("aluno", alu);
		
		oldEmail = alu.getEmail();
		oldCpf = alu.getCpf();
		
		return "editarAluno";
	}
	
	@PostMapping("/bibliotecario/editarAluno")
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
			if (alunoDAO.existsByEmail(email) || bibliotecarioDAO.existsByEmail(email)) {
				erros += "email$aluno$email já cadastrado$";
			}
		}
		
		if (!oldCpf.equals(cpf)) {
			if (alunoDAO.existsByCpf(cpf) || bibliotecarioDAO.existsByCpf(cpf)) {
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
			return "editarAluno";
		}
		
		this.alunoDAO.save(aluno);
		return "redirect:listarAlunos";
	}

	@GetMapping("/bibliotecario/excluirAluno")
	public String excluirAluno(Integer idAluno, RedirectAttributes ra) {
		if (!(idAluno == null)) {
			this.alunoDAO.deleteById(idAluno);
		}
		return "redirect:listarAlunos";
	}
	
	@GetMapping("/aluno/home")
	public String homeAluno() {
		return "homeAluno";
	}
	
	@GetMapping("/aluno/meuPerfilAluno")
	public String meuPerfil() {
		return "meuPerfilAluno";
	}
}