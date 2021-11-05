package com.libersoft.Acesso;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class LoginConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registro) {
		//registro.addInterceptor(new LoginInterceptor()).addPathPatterns(new String[] {"/usuraio", "/usuario/*"});
	}
}
