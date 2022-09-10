package udemy.blogpost.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableJpaRepositories("udemy.blogpost.demo.repository")
//@EntityScan("udemy.blogpost.demo.entity")
//@ComponentScan({"udemy.blogpost.demo"})//,"udemy.blogpost.dto"
@EnableSwagger2
@SpringBootApplication
public class TysonApplication implements CommandLineRunner{
	//for swagger the url
	//http://localhost:8081/swagger-ui.html#/
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	} 
	
	public static void main(String[] args) {
		SpringApplication.run(TysonApplication.class, args);
		System.out.println("om sairam");
	}
	
	//The CommandLineRunner
	//The run method in main main class first gets executed onstarting springboot application. 
	//We get run method on implementing the CommlandLineRunner Interface.
	@Override
	public void run(String... args) throws Exception {
		System.out.println("I am in command line runner...");
	}
}