package br.senai.sp.info.gerenciadepjs.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.senai.sp.info.gerenciadepjs.dao.UsuarioDAO;
import br.senai.sp.info.gerenciadepjs.model.Permissao;
import br.senai.sp.info.gerenciadepjs.model.Usuario;

@Component
public class CadastrarAdmJob implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent e) {
		System.out.println("[Jobs]: Cadastro automático de administrador iniciado");
		
		Usuario admin = new Usuario();
		admin.setEmail("admin@email.com");
		admin.setNome("Administrador");
		admin.setSenha("admin");
		admin.setSobrenome("do Sistema");
		admin.setPermissao(Permissao.ADMINISTRADOR);
		admin.setTelefone("1100000000");
		admin.hashearSenha();
		
		System.out.println("[Jobs]: Verificando se há o administrador padrão no banco...");
		
		if(usuarioDAO.buscarPorEmail(admin.getEmail()) == null) {
			
			System.out.println("[Jobs]: Ainda não existe o administrador padrão. Criando administrador...");
			usuarioDAO.persistir(admin);
			
		}else {
			System.out.println("[Jobs]: Administrador padrão já existe.");
		}
		
		System.out.println("[Jobs]: Administrador pronto para uso.");
		
	}

}
