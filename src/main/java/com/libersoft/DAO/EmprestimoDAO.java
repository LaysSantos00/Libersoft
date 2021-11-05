package com.libersoft.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libersoft.Model.Emprestimo;

@Repository
public interface EmprestimoDAO extends JpaRepository<Emprestimo, Integer> {

}
