package com.libersoft.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libersoft.Model.Aluno;

@Repository
public interface AlunoDAO extends JpaRepository<Aluno, Integer>{
	boolean existsByEmail(String email);
	boolean existsByCpf(String cpf);
}
