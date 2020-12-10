package kr.or.ksmart37.ksmart_mybatis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.or.ksmart37.ksmart_mybatis.interceptor.CommonInterceptor;
import kr.or.ksmart37.ksmart_mybatis.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private CommonInterceptor commonInterceptor;
	
	@Autowired
	private LoginInterceptor loginInterceptor; 
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/css/**")
				.excludePathPatterns("/js/**");
		
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/")
				.excludePathPatterns("/login")
				.excludePathPatterns("/logout")
				.excludePathPatterns("/css/**")
				.excludePathPatterns("/js/**");
	}

}
