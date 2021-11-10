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
		
		if (nome.replace(" ", "").length() < 7) {
			mensagem += "nome$bibliotecario$poucos caracteres$";
		}
		
		if (!Pattern.matches("^[a-zA-Z0-9 ]*$", nome)) {
			mensagem += "nome$bibliotecario$apenas é aceito letras, números e espaços$";
		}
		
		if (telefone.length() != 11) {
			mensagem += "telefone$bibliotecario$precisa conter 11 dígitos$";
		}
		
		return mensagem;
	}
	
	
}
