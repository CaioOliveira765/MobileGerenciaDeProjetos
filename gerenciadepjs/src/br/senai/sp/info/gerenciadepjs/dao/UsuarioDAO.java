package br.senai.sp.info.gerenciadepjs.dao;

import java.util.List;

import br.senai.sp.info.gerenciadepjs.model.Usuario;

public interface UsuarioDAO {

	public void persistir(Usuario obj);
	
	public void deletar(Usuario obj);
	
	public void alterar(Usuario obj);
	
	public List<Usuario> buscarTodos();
	
	public Usuario buscar(Long id);
	
	public Usuario buscarPorNomeSobrenome(String nome, String sobrenome);
	
	public Usuario buscarPorEmail(String email);
	
	public Usuario buscarPorEmailESenha(String email, String senha);

}
