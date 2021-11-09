package com.libersoft.Service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.libersoft.Model.Bibliotecario;

@Service
public class BibliotecarioValidationService {
	public String validarBibliotecario(Bibliotecario bibliotecario) {
		String mensagem = "";
		
		String nome = bibliotecario.getNome();
		String email = bibliotecario.getEmail();
		String senha = bibliotecario.getSenha();
		String telefone = bibliotecario.getTelefone();
		String cpf = bibliotecario.getCpf();
		
		if (cpf.length() != 11) {
			mensagem += "cpf$bibliotecario$precisa conter 11 dígitos$";
		}
		
		if (telefone.length() != 11) {
			mensagem += "telefone$bibliotecario$precisa conter 11 dígitos$";
		}
		
		if (Pattern.matches("/^[^\\\\s@]+@[^\\\\s@]+\\\\.[^\\\\s@]+$/", email)) {
			mensagem += "email$bibliotecario$formato aceito: [endereço]@[domínio].[extensão]$";
		}
		
		return mensagem;
	}
}
