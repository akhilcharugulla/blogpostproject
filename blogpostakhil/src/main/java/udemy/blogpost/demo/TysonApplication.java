package udemy.blogpost.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories("udemy.blogpost.demo.repository")
@EntityScan("udemy.blogpost.demo.entity")
@ComponentScan({"udemy.blogpost.demo"})//,"udemy.blogpost.dto"
@EnableSwagger2
public class TysonApplication {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	} 
	
	public static void main(String[] args) {
		SpringApplication.run(TysonApplication.class, args);
		System.out.println("om sairam");
	}
}