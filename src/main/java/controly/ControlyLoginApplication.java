package controly;

import controly.model.entity.TopicoEntity;
import controly.model.service.TopicoService;
import controly.repository.TopicoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ControlyLoginApplication {
	public static void main(String[] args) {
		SpringApplication.run(ControlyLoginApplication.class, args);
	}

}
