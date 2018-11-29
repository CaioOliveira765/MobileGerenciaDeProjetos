package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.ui.Model;

import br.senai.sp.info.gerenciadepjs.controller.UsuarioController;

public class UsuarioControllerTest {
	
//	@Inject
	private UsuarioController usuarioController;
	
//	@Mock
	private Model model;
	
	@Test
	public void testarAbrirLogin() {
		
		String retorno = usuarioController.logout();
		
		assertEquals("index", retorno);
	}
	
}
