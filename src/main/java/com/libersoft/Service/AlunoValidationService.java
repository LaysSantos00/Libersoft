package com.libersoft.Service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.libersoft.Model.Aluno;

@Service
public class AlunoValidationService {
	public String validarAluno (Aluno aluno) {		
		String mensagem = "";
		
		String nome = aluno.getNome();
		String email = aluno.getEmail();
		String senha = aluno.getSenha();
		String telefone = aluno.getTelefone();
		String endereco = aluno.getEndereco();
		String cpf = aluno.getCpf();
		
		if (cpf.length() != 11) {
			mensagem += "cpf$aluno$precisa conter 11 dígitos$";
		}
		
		if (telefone.length() != 11) {
			mensagem += "telefone$aluno$precisa conter 11 dígitos$";
		}
		
		if (Pattern.matches("/^[^\\\\s@]+@[^\\\\s@]+\\\\.[^\\\\s@]+$/", email)) {
			mensagem += "email$aluno$formato aceito: [endereço]@[domínio].[extensão]$";
		}
		
		return mensagem;
	}
}
