package com.libersoft.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libersoft.Model.Livro;

@Repository
public interface LivroDAO extends JpaRepository<Livro, Integer>{
	boolean existsByIsbn(long isbn);
}
