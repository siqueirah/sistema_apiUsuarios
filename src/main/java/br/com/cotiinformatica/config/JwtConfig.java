package br.com.cotiinformatica.config;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.cotiinformatica.security.JwtSecurityFilter;
@Configuration
public class JwtConfig {
	@Bean
	public FilterRegistrationBean<JwtSecurityFilter> jwtFilter() {
		
		FilterRegistrationBean<JwtSecurityFilter> filter = new FilterRegistrationBean<JwtSecurityFilter>();
		filter.setFilter(new JwtSecurityFilter());
		
		//mapear os endpoints da API que exijem autenticação
		filter.addUrlPatterns("/api/produtos");
		return filter;
	}	
}