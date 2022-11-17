package controly.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@Profile("prod")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                .logout().logoutSuccessUrl("/login.html")
                .and()
                .csrf().disable();

        return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
