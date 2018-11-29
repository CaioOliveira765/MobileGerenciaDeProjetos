package br.senai.sp.info.gerenciadepjs.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import br.senai.sp.info.gerenciadepjs.model.Usuario;
import br.senai.sp.info.gerenciadepjs.utils.JwtUtils;

@Component
public class JwtFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		
		String authorization = request.getHeader("Authorization");
		
		if(authorization != null) {
									// BEARER AQUI
			if(authorization.matches("(Bearer )(\\w|\\.|\\-)+")) {
				String token = authorization.split(" ")[1];
				
				System.out.println(token);
				
				try {
					JwtUtils.validarToken(token);
					
					Usuario usuarioToken = JwtUtils.extrairUsuarioDoToken(token);
					
					SecurityContextHolder.getContext().setAuthentication(usuarioToken);
				
				}catch (Exception e) {
					System.out.println("Token inválido - dados incorretos");
				}
			}else {
				System.out.println("Token inválido - formato");
			}
		}else {
			System.out.println("Authorization não informado");
		}
		chain.doFilter(req, res);
	}
}

