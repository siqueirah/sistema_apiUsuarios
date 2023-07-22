package br.com.cotiinformatica;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import br.com.cotiinformatica.dtos.AutenticarRequestDto;
import br.com.cotiinformatica.dtos.AutenticarResponseDto;
import br.com.cotiinformatica.dtos.CriarContaRequestDto;
import br.com.cotiinformatica.dtos.ProdutosGetResponseDto;
import br.com.cotiinformatica.dtos.ProdutosPostRequestDto;
import br.com.cotiinformatica.dtos.ProdutosPutRequestDto;
import br.com.cotiinformatica.dtos.RecuperarSenhaRequestDto;
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SistemaApiusuariosApplicationTests {
	@Autowired // inicialização automática!
	private MockMvc mockMvc;
	@Autowired // inicialização automática!
	private ObjectMapper objectMapper;
	
	//atributos
	private static String emailUsuario;
	private static String senhaUsuario;
	private static String accessToken;
	private static Integer idProduto;
	@Test
	@Order(1)
	public void criarContaTest() throws Exception {
		Faker faker = new Faker();
		
		CriarContaRequestDto dto = new CriarContaRequestDto();
		dto.setNome(faker.name().fullName());
		dto.setEmail(faker.internet().emailAddress());
		dto.setSenha("@Teste1234");
		
		mockMvc.perform(post("/api/usuarios/criar-conta")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated());
		
		emailUsuario = dto.getEmail();
		senhaUsuario = dto.getSenha();
	}
	@Test
	@Order(2)
	public void autenticarTest() throws Exception {
		
		AutenticarRequestDto dto = new AutenticarRequestDto();
		dto.setEmail(emailUsuario);
		dto.setSenha(senhaUsuario);
		
		MvcResult result = mockMvc.perform(post("/api/usuarios/autenticar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andReturn();
		
		String responseBody = result.getResponse().getContentAsString();
		AutenticarResponseDto response = objectMapper.readValue(responseBody, AutenticarResponseDto.class);
		
		accessToken = response.getAccessToken();
	}
	@Test
	@Order(3)
	public void recuperarSenhaTest() throws Exception{
		
		RecuperarSenhaRequestDto dto = new RecuperarSenhaRequestDto();
		dto.setEmail(emailUsuario);
		
		mockMvc.perform(post("/api/usuarios/recuperar-senha")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk());
	}
	
	@Test
	@Order(4)
	public void cadastrarProdutoTest() throws Exception {
		
		Faker faker = new Faker();
		
		ProdutosPostRequestDto dto = new ProdutosPostRequestDto();
		dto.setNome(faker.commerce().productName());
		dto.setPreco(faker.number().randomDouble(2, 1, 1000));
		dto.setQuantidade(faker.number().randomDigit());
		
		MvcResult result = mockMvc.perform(post("/api/produtos")
				.header("Authorization", "Bearer " + accessToken)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andReturn();
		
		String responseBody = result.getResponse().getContentAsString();
		ProdutosGetResponseDto response = objectMapper.readValue(responseBody, ProdutosGetResponseDto.class);
		
		idProduto = response.getIdProduto();
	}
	@Test
	@Order(5)
	public void atualizarProdutoTest() throws Exception {
		
		Faker faker = new Faker();
		
		ProdutosPutRequestDto dto = new ProdutosPutRequestDto();
		dto.setIdProduto(idProduto);
		dto.setNome(faker.commerce().productName());
		dto.setPreco(faker.number().randomDouble(2, 1, 1000));
		dto.setQuantidade(faker.number().randomDigit());
		
		mockMvc.perform(put("/api/produtos")
				.header("Authorization", "Bearer " + accessToken)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk());
	}
	
	@Test
	@Order(6)
	public void consultarProdutoTest() throws Exception {
		
		mockMvc.perform(get("/api/produtos/" + idProduto)
				.header("Authorization", "Bearer " + accessToken))
				.andExpect(status().isOk());		
	}
	
	@Test
	@Order(7)
	public void excluirProdutoTest() throws Exception {
		
		mockMvc.perform(delete("/api/produtos/" + idProduto)
				.header("Authorization", "Bearer " + accessToken))
				.andExpect(status().isOk());
	}
}


