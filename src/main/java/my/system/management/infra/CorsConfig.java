package my.system.management.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class CorsConfig implements WebMvcConfigurer  {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173/")
                .allowedHeaders("Authorization", "Content-Type")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowCredentials(false);
    }
}
