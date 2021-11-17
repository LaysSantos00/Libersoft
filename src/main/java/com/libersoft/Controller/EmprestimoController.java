package com.libersoft.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libersoft.DAO.AlunoDAO;
import com.libersoft.DAO.EmprestimoDAO;
import com.libersoft.DAO.LivroDAO;
import com.libersoft.Model.Aluno;
import com.libersoft.Model.Emprestimo;
import com.libersoft.Model.Livro;

@Controller
public class EmprestimoController {
	@Autowired
	private EmprestimoDAO emprestimoDAO;
	
	@Autowired
	private AlunoDAO alunoDAO;
	
	@Autowired
	private LivroDAO livroDAO;

	@ModelAttribute("emprestimos")
	public List<Emprestimo> getLista() {
		return this.emprestimoDAO.findAll();
	}

	@GetMapping("/bibliotecario/listarEmprestimos")
	public String listarEmprestimos(RedirectAttributes ra, Emprestimo emprestimo) {		
		return "listarEmprestimos";
	}
	
	@GetMapping("/bibliotecario/cadastroEmprestimo")
	public String exibirFormEmprestimo(Emprestimo emprestimo) {
		return "cadastroEmprestimo";
	}

	@PostMapping("/bibliotecario/cadastroEmprestimo")
	public String cadastrarEmprestimo(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra,
										Emprestimo emprestimo) throws ParseException {
		
		/*
		 * TIRA AS MASKS DOS VALORES INSERIDOS
		 */
		String cpf = request.getParameter("cpf")
							.replace(".", "")
							.replace("-", "")
							.replace("_", "");
		
		String isbnString = request.getParameter("isbn")
							 .replace("_", "");
		
		String dataEmprestimoString = request.getParameter("data_emprestimo")
											 .replace("-", "/");
		Date dataEmprestimoUtil = new SimpleDateFormat("yyyy/MM/dd").parse(dataEmprestimoString);
		
		java.sql.Date dataEmprestimo = new java.sql.Date(dataEmprestimoUtil.getTime());
		
		String dataDevolucaoString = request.getParameter("data_devolucao")
											.replace("-", "/");
		Date dataDevolucaoUtil = new SimpleDateFormat("yyyy/MM/dd").parse(dataDevolucaoString);
		
		java.sql.Date dataDevolucao = new java.sql.Date(dataDevolucaoUtil.getTime());
		
		long isbn = 0;
		String erros = "";
		
		/*
		 *  VERIFICAR TAMANHO DOS CAMPOS
		 */
		if (cpf.length() != 11) {
			ra.addFlashAttribute("mensagem", "CPF inválido: precisa conter 11 dígitos");
		}
		
		if (isbnString.length() == 10) {
			isbn = Long.parseLong(isbnString);
		} else {
			ra.addFlashAttribute("mensagem", "ISBN inválido: precisa conter 10 dígitos");
		}
		
		
		Aluno aluno = this.alunoDAO.findByCpf(cpf);
		Livro livro = this.livroDAO.findByIsbn(isbn);
		
		/*
		 * VERIFICAR SE ALUNO E LIVRO FORNECIDOS ESTÃO CADASTRADOS NO SISTEMA
		 */
		
		if (aluno == null) {
			ra.addFlashAttribute("mensagem", "Aluno inválido: não há cadastrado");
		}
		
		if (livro == null) {
			ra.addFlashAttribute("mensagem", "ISBN inválido: não há cadastrado");
		}
		
		/*
		 * SE ALGUM DOS DOIS NÃO TIVER CADASTRADO, RETORNA PARA
		 * A TELA DE CADASTRO DO EMPRÉSTIMO
		 * JUNTO COM OS ERROS
		 */
		
		if (aluno == null || livro == null) {
			return "cadastroEmprestimo";
		} else {
			emprestimo.setIdAluno(aluno);
			emprestimo.setIdLivro(livro);
			emprestimo.setRenovacoes(3);
			emprestimo.setDataDevolucao(dataDevolucao);
			emprestimo.setDataEmprestimo(dataEmprestimo);
			emprestimo.setSituacao("Ok");
			this.emprestimoDAO.save(emprestimo);
			return "redirect:listarEmprestimos";
		}
		
	}

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
