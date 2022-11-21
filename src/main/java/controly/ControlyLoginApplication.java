package controly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ControlyLoginApplication {
	public static void main(String[] args) {
		SpringApplication.run(ControlyLoginApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("12345678"));
	}

}
