package com.libersoft.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libersoft.Model.Bibliotecario;

@Repository
public interface BibliotecarioDAO extends JpaRepository<Bibliotecario, Integer> {
	boolean existsByEmail(String email);
	boolean existsByCpf(String cpf);
	
	@Query("SELECT u FROM Bibliotecario u WHERE u.cpf = :cpf AND u.senha = :senha")
	public Bibliotecario findByLoginAndSenha(String cpf, String senha);
}
