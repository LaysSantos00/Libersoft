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
		
		/*
		 * 
		 * DEIXANDO ESSE SERVICE CASO PRECISE SER USADO
		 * 
		 */
		
		return mensagem;
	}
}
