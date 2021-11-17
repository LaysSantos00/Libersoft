package com.libersoft.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libersoft.Model.Administrador;

@Repository
public interface AdministradorDAO extends JpaRepository<Administrador, Integer> {
	
	//boolean existsByCodigo(String codigoDeAcesso);

	@Query("SELECT u FROM Administrador u WHERE u.usuario = :codigoDeAcesso AND u.senha = :senha")
	public Administrador findByUsuarioAndSenha(String codigoDeAcesso, String senha);

}
