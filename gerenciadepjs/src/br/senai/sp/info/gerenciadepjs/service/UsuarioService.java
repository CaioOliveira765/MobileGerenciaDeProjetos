package br.senai.sp.info.gerenciadepjs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.senai.sp.info.gerenciadepjs.dao.UsuarioDAO;
import br.senai.sp.info.gerenciadepjs.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.gerenciadepjs.exceptions.ValidacaoException;
import br.senai.sp.info.gerenciadepjs.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO dao;
	
	public Usuario buscarPorEmailESenha(Usuario usuario, BindingResult bindingResult) throws ValidacaoException, EntidadeNaoEncontradaException{
		
		if (bindingResult.hasFieldErrors("email") || bindingResult.hasFieldErrors("senha")) {
			throw new ValidacaoException();
		}
		
		usuario.hashearSenha();
		Usuario usuarioBuscado = dao.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha());
		
		if(usuarioBuscado == null) {
			throw new EntidadeNaoEncontradaException();
		}
		return usuarioBuscado;
	}
	
	public Usuario buscarPorEmail(Usuario usuario, BindingResult br) throws ValidacaoException, EntidadeNaoEncontradaException {
		if (br.hasFieldErrors("email")){
			throw new ValidacaoException();
		}
		if (dao.buscarPorEmail(usuario.getEmail()) == null){
			throw new EntidadeNaoEncontradaException();
		}
		return usuario;
	}
}
