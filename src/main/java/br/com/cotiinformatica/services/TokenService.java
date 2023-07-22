package br.com.cotiinformatica.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	/*
	 * Atributo para ler o valor da chave antifalsificação
	 * mapeada no arquivo application.properties do projeto
	 */
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	/*
	 * Método para fazer a geração dos tokens JWT para este projeto
	 * cada token deverá guardar o email do usuário autenticado
	 */
	public String generateToken(String emailUsuario) throws Exception {
		
		return Jwts.builder()
				.setSubject(emailUsuario) //email do usuário autenticado
				.setIssuedAt(new Date()) //data de geração do token
				.signWith(SignatureAlgorithm.HS256, jwtSecret) //assinatura antifalsificação
				.compact(); //finalizando e retornando o token
	}
}
