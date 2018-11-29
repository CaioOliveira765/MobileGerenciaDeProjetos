package br.senai.sp.info.gerenciadepjs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.senai.sp.info.gerenciadepjs.model.Permissao;
import br.senai.sp.info.gerenciadepjs.model.Usuario;

@Component
public class AutenticacaoInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private HttpSession session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
															// Adicionar a rota abaixo
		boolean necessitaAutenticacao = request.getRequestURI().contains("/app"); // aqui
		Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		boolean usuarioEstaAutenticado = usuarioAutenticado != null;
		boolean necessitaSerAdministrador = request.getRequestURI().contains("/adm");
		
		if(necessitaAutenticacao) {
			if(usuarioEstaAutenticado) {
				if(necessitaSerAdministrador && usuarioAutenticado.getPermissao() != Permissao.ADMINISTRADOR) {
					response.sendError(403);
					return false;
				}
			}else {
				response.sendError(401);
				return false;
			}
		}
			return true;
	}

}
