package br.com.cotiinformatica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	/*
		Método para consultar 1 usuário no banco de dados
		através do email (JPQL - Java Persistence Query Language)
	*/
	@Query("from Usuario u where u.email = :pEmail")
	Usuario findByEmail(@Param("pEmail") String email) throws Exception;
	
	/*
	 	Método para consultar 1 usuário no banco de dados
	 	através do email e da senha (JPQL - Java Persistence Query Language)
	 */
	@Query("from Usuario u where u.email = :pEmail and u.senha = :pSenha")
	Usuario findByEmailAndSenha(@Param("pEmail") String email, @Param("pSenha") String senha) throws Exception;
}
