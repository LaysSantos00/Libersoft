package com.libersoft.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libersoft.Model.Adm;

@Repository
public interface AdmDAO extends JpaRepository<Adm, Integer> {
	
	//boolean existsByCodigo(String codigoDeAcesso);

	@Query("SELECT u FROM Adm u WHERE u.codigoDeAcesso = :codigoDeAcesso AND u.senha = :senha")
	public Adm findByCodigoAndSenha(String codigoDeAcesso, String senha);

}
