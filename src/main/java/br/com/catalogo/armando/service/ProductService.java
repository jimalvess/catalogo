package br.com.catalogo.armando.service;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.catalogo.armando.model.Product;
import br.com.catalogo.armando.repositorio.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ResponseEntity<?> save(Product product) {
		return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
	}

	public ResponseEntity<?> updateById(String id, Product product) {
		product.setId(id);
		return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
	}

	public ResponseEntity<?> findById(String id) {
		ResponseEntity<?> ret = null;
		ret = new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
		if (ret.getBody().equals(Optional.empty())) {
			ret = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ret;
	}

	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<?> delete(String id) {
		ResponseEntity<?> ret = new ResponseEntity<>(HttpStatus.OK);
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			ret = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ret;
	}

	public ResponseEntity<?> findBySearch(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		return new ResponseEntity<>(productRepository.findByPriceBetweenAndNameAndDescription(minPrice, maxPrice, q, q), HttpStatus.OK);
	}
}
