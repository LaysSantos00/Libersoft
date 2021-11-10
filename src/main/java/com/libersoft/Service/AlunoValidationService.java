package com.libersoft.Service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.libersoft.Model.Aluno;

@Service
public class AlunoValidationService {
	public String validarAluno (Aluno aluno) {		
		String mensagem = ""; // PADRÃO: 'campo$tabela$erro$';
		
		String nome = aluno.getNome();
		String email = aluno.getEmail();
		String senha = aluno.getSenha();
		String telefone = aluno.getTelefone();
		String endereco = aluno.getEndereco();
		String cpf = aluno.getCpf();
		
		if (nome.replace(" ", "").length() < 7) {
			mensagem += "nome$aluno$poucos caracteres$";
		}
		
		if (!Pattern.matches("^[a-zA-Z0-9 ]*$", nome)) {
			mensagem += "nome$aluno$apenas é aceito letras, números e espaços$";
		}
		
		if (telefone.length() != 11) {
			mensagem += "telefone$aluno$precisa conter 11 dígitos$";
		}
		
		return mensagem;
	}
	
	
}
