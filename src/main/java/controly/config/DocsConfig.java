package controly.config;

import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Hidden
@RestController
@Configuration
public class DocsConfig {



    @Hidden
    @RequestMapping("/docs")
    public void apiDocumentation(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui/index.html");
    }
    @Hidden
    @RequestMapping("/doc")
    public void apiDocumentation2(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui/index.html");
    }

}
