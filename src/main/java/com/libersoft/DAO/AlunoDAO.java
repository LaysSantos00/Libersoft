package com.libersoft.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libersoft.Model.Aluno;

@Repository
public interface AlunoDAO extends JpaRepository<Aluno, Integer>{
	boolean existsByEmail(String email);
	boolean existsByCpf(String cpf);
	
	@Query("SELECT u FROM Aluno u WHERE u.cpf = :cpf AND u.senha = :senha")
	public Aluno findByLoginAndSenha(String cpf, String senha);
	
	public Aluno findByCpf(String cpf);
}
