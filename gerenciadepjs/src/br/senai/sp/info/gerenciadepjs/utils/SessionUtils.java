package br.senai.sp.info.gerenciadepjs.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.senai.sp.info.gerenciadepjs.model.Usuario;


@Component
public class SessionUtils {

	@Autowired
	private HttpSession session;
	
	public static final String CHAVE_USUARIO_AUTENTICADO = "usuarioAutenticado";
	
	public Usuario getUsuarioLogado() {
		return (Usuario) session.getAttribute(CHAVE_USUARIO_AUTENTICADO);
	}
	
	public void setUsuarioLogado(Usuario usuario) {
		session.setAttribute(CHAVE_USUARIO_AUTENTICADO, usuario);
	}
	
	public boolean isUsuarioLogado() {
		return session.getAttribute(CHAVE_USUARIO_AUTENTICADO) != null;
	}
	
	public void invalidarSessao() {
		session.invalidate();
	}
	
}
