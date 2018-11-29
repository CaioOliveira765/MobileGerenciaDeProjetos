package br.senai.sp.info.gerenciadepjs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity					// WSCA
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {          
	    http
	        .antMatcher("/app/**")// Add this
	        .httpBasic().and()
	        .authorizeRequests()
	        .antMatchers("/usuario/autenticar").permitAll()
	        .antMatchers("/api/**").authenticated()
	        .antMatchers(HttpMethod.GET,
	                "/*.html",
	                "/favicon.ico",
	                "/**/*.html",
	                "/**/*.css",
	                "/**/*.eot",
	                "/**/*.ttf",
	                "/**/*.woff2",
	                "/**/*.woff",
	                "/**/*.svg",
	                "/**/*.js").permitAll()
	        .and()        
	        .formLogin().loginPage("/index")
	        .loginProcessingUrl("/usuario/autenticar")
	        .defaultSuccessUrl("/app/tecnologia")
	        .loginPage("/index").permitAll()
	        .and()
	        	.logout().permitAll()
	        .and()
	        	.csrf().disable().cors();
	}
}
