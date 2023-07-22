package br.com.cotiinformatica.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity // entidade do banco de dados
@Table(name = "usuario") // nome da tabela
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {

	@Id // chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremento
	@Column(name = "idusuario") // coluna
	private Integer idUsuario;

	@Column(name = "nome", length = 150, nullable = false) // coluna
	private String nome;

	@Column(name = "email", length = 100, nullable = false, unique = true) // coluna
	private String email;

	@Column(name = "senha", length = 40, nullable = false) // coluna
	private String senha;

}
