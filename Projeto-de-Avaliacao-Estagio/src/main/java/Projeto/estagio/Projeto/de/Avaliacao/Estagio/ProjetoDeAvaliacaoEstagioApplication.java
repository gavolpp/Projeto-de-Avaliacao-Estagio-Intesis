package Projeto.estagio.Projeto.de.Avaliacao.Estagio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class ProjetoDeAvaliacaoEstagioApplication implements Connection{

	public static void main(String[] args) {
		SpringApplication.run(ProjetoDeAvaliacaoEstagioApplication.class, args);
		
	}
		@Override
    	public void addCorsMappings(CorsRegistry registry) {
        // Permite CORS para o frontend
        registry.addMapping("/**") 
                .allowedOrigins("http://127.0.0.1:5500")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
        
    	}
}


