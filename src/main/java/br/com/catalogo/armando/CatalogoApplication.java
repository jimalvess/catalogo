package br.com.catalogo.armando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.catalogo.armando.endpoint.controller")
@EntityScan("br.com.catalogo.armando.model")
@EnableJpaRepositories("br.com.catalogo.armando.repositorio")
@EnableSwagger2
@ComponentScan("br.com.catalogo.armando")
public class CatalogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}
}
