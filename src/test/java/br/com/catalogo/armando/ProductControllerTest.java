package br.com.catalogo.armando;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import br.com.catalogo.armando.model.Product;
import br.com.catalogo.armando.repositorio.ProductRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private ProductRepository productRepository;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getAllTest200() throws Exception {
		Product product = new Product("135", "name", "Description", BigDecimal.TEN);
		BDDMockito.when(productRepository.save(product)).thenReturn(product);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/products").contentType("application/json").accept("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void postProductTest201() {
		Product product = new Product(null, "name", "Description", BigDecimal.TEN);
		Product productRet = new Product("9898", "name", "Description", BigDecimal.TEN);
		BDDMockito.when(productRepository.save(product)).thenReturn(productRet);
		ResponseEntity<Product> response = restTemplate.postForEntity("/products", product, Product.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
		Assertions.assertThat(response.getBody().getId()).isNotNull();
	}

	@Test
	public void postProductTest400() {
		Product product = new Product("123", null, "Description", BigDecimal.TEN);
		BDDMockito.when(productRepository.save(product)).thenReturn(product);
		ResponseEntity<Product> response = restTemplate.postForEntity("/products", product, Product.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void putProductTest400() throws Exception {
		Product product = new Product("123", null, "Description", BigDecimal.TEN);
		mockMvc.perform(put("/products/{id}", product).contentType("application/json").accept("application/json"))
				.andExpect(status().isBadRequest());
	}
}
