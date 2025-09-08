package br.com.controly.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Configuration
public class DocsConfig {



    @RequestMapping("/docs")
    public void apiDocumentation(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui/index.html");
    }
    @RequestMapping("/doc")
    public void apiDocumentation2(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui/index.html");
    }

}
