package com.libersoft.Login;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.libersoft.Controller.AdmInterceptor;
import com.libersoft.Controller.AlunoInterceptor;
import com.libersoft.Controller.BibliotecarioInterceptor;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor((HandlerInterceptor) new AlunoInterceptor())
				.addPathPatterns(new String[] { "/aluno", "/aluno/**" })
				.excludePathPatterns("/assets/**", "/css/**", "/img/**");
		
		registry.addInterceptor((HandlerInterceptor) new BibliotecarioInterceptor())
				.addPathPatterns(new String[] { "/bibliotecario", "/bibliotecario/**" })
				.excludePathPatterns("/assets/**", "/css/**", "/img/**");;
		
		registry.addInterceptor((HandlerInterceptor) new AdmInterceptor())
				.addPathPatterns(new String[] { "/adm", "/adm/**" })
				.excludePathPatterns("/assets/**", "/css/**", "/img/**");;
	}
	
	@Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry
	      .addResourceHandler("/adm/**")
	      .addResourceLocations("classpath:/static/");
	  }
}