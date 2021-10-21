package br.com.catalogo.armando.repositorio;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import br.com.catalogo.armando.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
	List<Product> findByPriceBetweenAndNameAndDescription(BigDecimal priceMin, BigDecimal priceMax, String name, String description);
}