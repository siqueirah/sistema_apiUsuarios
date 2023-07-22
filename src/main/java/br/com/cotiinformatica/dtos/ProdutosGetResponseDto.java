package br.com.cotiinformatica.dtos;

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

public class ProdutosGetResponseDto {

	private Integer IdProduto;
	private String Nome;
	private Double Preco;
	private Integer Quantidade;
	private Double Total;
	
}
