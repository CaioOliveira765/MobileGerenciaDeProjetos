package br.senai.sp.info.gerenciadepjs.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.senai.sp.info.gerenciadepjs.config.PersistenceConfig;
import br.senai.sp.info.gerenciadepjs.interceptor.AutenticacaoInterceptor;

@Configuration
@Import(value = PersistenceConfig.class)
@EnableWebMvc
@ComponentScan(value = "br.senai.sp.info.gerenciadepjs")
public class AppConfig implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		registry.viewResolver(resolver);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	
		registry
		.addResourceHandler("/assets/**")
		.addResourceLocations("/assets/");
	
		registry
		.addResourceHandler("/resources/**")
		.addResourceLocations("/resources/");
	}
		
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages/validations");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		
		return messageSource;
	}
	
	@Bean
	public AutenticacaoInterceptor getAutenticacaoInterceptor() {
		return new AutenticacaoInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(getAutenticacaoInterceptor())
				.addPathPatterns("/**");
	}
	
}
