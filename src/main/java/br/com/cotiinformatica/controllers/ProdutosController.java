package br.com.cotiinformatica.controllers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.cotiinformatica.dtos.ProdutosGetResponseDto;
import br.com.cotiinformatica.dtos.ProdutosPostRequestDto;
import br.com.cotiinformatica.dtos.ProdutosPutRequestDto;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.repositories.ProdutoRepository;
import jakarta.validation.Valid;
@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutosController {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	public ResponseEntity<ProdutosGetResponseDto> post(@RequestBody @Valid ProdutosPostRequestDto dto) {
		try {
			Produto produto = new Produto();
			
			produto.setNome(dto.getNome());
			produto.setPreco(dto.getPreco());
			produto.setQuantidade(dto.getQuantidade());
			
			produtoRepository.save(produto);
			
			ProdutosGetResponseDto response = parse(produto);			
			return ResponseEntity.status(201).body(response);
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	
	@PutMapping
	public ResponseEntity<ProdutosGetResponseDto> put(@RequestBody @Valid ProdutosPutRequestDto dto) {
		
		try {
			
			Produto produto = produtoRepository.findById(dto.getIdProduto()).get();
			
			produto.setNome(dto.getNome());
			produto.setPreco(dto.getPreco());
			produto.setQuantidade(dto.getQuantidade());
			
			produtoRepository.save(produto);
			ProdutosGetResponseDto response = parse(produto);
			
			return ResponseEntity.status(200).body(response);
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ProdutosGetResponseDto> delete(@PathVariable("id") Integer id) {
		try {
			
			Produto produto = produtoRepository.findById(id).get();
			produtoRepository.delete(produto);
			
			ProdutosGetResponseDto response = parse(produto);
			return ResponseEntity.status(200).body(response);
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body(null);
		}		
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutosGetResponseDto>> getAll() {
		try {
			
			List<ProdutosGetResponseDto> lista = new ArrayList<ProdutosGetResponseDto>();
			
			for(Produto produto : produtoRepository.findAll()) {
				lista.add(parse(produto));
			}
			
			return ResponseEntity.status(200).body(lista);
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProdutosGetResponseDto> getById(@PathVariable("id") Integer id) {
		
		try {
			
			Produto produto = produtoRepository.findById(id).get();
			ProdutosGetResponseDto response = parse(produto);
			
			return ResponseEntity.status(200).body(response);
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	
	
	private ProdutosGetResponseDto parse(Produto produto) {
		
		ProdutosGetResponseDto response = new ProdutosGetResponseDto();
		response.setIdProduto(produto.getIdProduto());
		response.setNome(produto.getNome());
		response.setPreco(produto.getPreco());
		response.setQuantidade(produto.getQuantidade());
		response.setTotal(produto.getPreco() * produto.getQuantidade());
		
		return response;
	}	
}



