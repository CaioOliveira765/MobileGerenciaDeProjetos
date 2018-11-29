package br.senai.sp.info.gerenciadepjs.jobs;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.senai.sp.info.gerenciadepjs.dao.UsuarioDAO;
import br.senai.sp.info.gerenciadepjs.model.Usuario;
import br.senai.sp.info.gerenciadepjs.utils.EmailUtils;

@Component
public class EnviarSenhaJob {

	@Autowired
	private UsuarioDAO usuarioDAO;

	public void gerarEnviarSenha(Usuario usuario) {

		if (!usuario.getEmail().equals("admin@email.com")) {
			String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
					"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
			String senhaNova = "";

			for (int x = 0; x < 6; x++) {
				int j = (int) (Math.random() * carct.length);
				senhaNova += carct[j];
			}

			Usuario usuarioBanco = usuarioDAO.buscarPorEmail(usuario.getEmail());
			usuarioBanco.setSenha(senhaNova);
			usuarioBanco.hashearSenha();
			usuarioDAO.alterar(usuarioBanco);

			String titulo = "BQR - recuperação de senha";
			String corpo = "Olá, " + usuarioBanco.getNome() + " " + usuarioBanco.getSobrenome()
					+ "! Recupere sua senha." + " A nova senha é: " + senhaNova;

			try {
				EmailUtils.enviarEmail(titulo, corpo, usuario.getEmail());
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
