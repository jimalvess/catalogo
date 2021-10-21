package br.com.catalogo.armando.endpoint.controller;

import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.catalogo.armando.model.Product;
import br.com.catalogo.armando.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/products")
@Api(value = "Controller de produto")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	@ApiOperation(value = "Todos os produtos", response = Product.class)
	public ResponseEntity<?> getProdutos() {
		log.info("Show all products");
		return productService.findAll();
	}

	@PostMapping
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "Inserir produto", response = Product.class)
	public ResponseEntity<?> productsPost(@Valid @RequestBody Product product) {
		log.info("Insert Product");
		return productService.save(product);
	}

	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Alteração de produto", response = Product.class)
	public ResponseEntity<?> productsPut(@PathVariable("id") String id,@Valid @RequestBody Product product) {
		log.info("Update product");
		return productService.updateById(id, product);
	}

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Mostra um produto", response = Product.class)
	public ResponseEntity<?> getProdutoPorId(@PathVariable("id") String id) {
		log.info("Show one product");
		return productService.findById(id);
	}

	@GetMapping("/search")
	@ApiOperation(value = "Busca por produtos", response = Product.class)
	public ResponseEntity<?> getProdutosFiltrados(@RequestParam("q") String q,
			@RequestParam("min_price") BigDecimal minPrice, @RequestParam("max_price") BigDecimal maxPrice) {
		log.info("Search products");
		return productService.findBySearch(q, minPrice, maxPrice);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Excluir um produto", response = Product.class)
	public ResponseEntity<?> deleteProduto(@NotBlank @PathVariable("id") String id) {
		log.info("Remove a product");
		return productService.delete(id);
	}

}
