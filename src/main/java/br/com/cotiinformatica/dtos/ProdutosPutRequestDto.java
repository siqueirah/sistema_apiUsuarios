package br.com.cotiinformatica.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutosPutRequestDto {
	
	@Min(value = 1, message = "Por favor, informe um valor maior ou igual a 1.")
	private Integer idProduto;
		
	@NotBlank(message = "Por favor, informe o nome do produto.")
	private String nome;
	@Min(value = 0, message = "Por favor, informe um valor maior ou igual a zero.")
	private Double preco;
	@Min(value = 0, message = "Por favor, informe um valor maior ou igual a zero.")
	private Integer quantidade;
}


