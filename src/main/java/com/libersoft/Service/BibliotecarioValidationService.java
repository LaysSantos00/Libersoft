package com.libersoft.Service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.libersoft.Model.Bibliotecario;

@Service
public class BibliotecarioValidationService {
	public String validarBibliotecario(Bibliotecario bibliotecario) {
		String mensagem = ""; // PADRÃO: 'campo$tabela$erro$';
		
		String nome = bibliotecario.getNome();
		String email = bibliotecario.getEmail();
		String senha = bibliotecario.getSenha();
		String telefone = bibliotecario.getTelefone();
		String cpf = bibliotecario.getCpf();
		
		/*
		 * 
		 * DEIXANDO ESSE SERVICE CASO PRECISE SER USADO
		 * 
		 */
		
		return mensagem;
	}
}
