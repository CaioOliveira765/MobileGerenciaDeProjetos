package br.senai.sp.info.gerenciadepjs.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.gerenciadepjs.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.gerenciadepjs.exceptions.ValidacaoException;
import br.senai.sp.info.gerenciadepjs.jobs.EnviarSenhaJob;
import br.senai.sp.info.gerenciadepjs.model.Usuario;
import br.senai.sp.info.gerenciadepjs.service.UsuarioService;

@RestController
@RequestMapping("/rest/usuario")
public class UsuarioRestController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EnviarSenhaJob job;
	
	@PutMapping("/enviarsenha")
	public ResponseEntity<Object> enviarSenhaPeloEmail(@RequestBody Usuario usuario, BindingResult brUsuario){				

		try {			
			Usuario usuarioBanco = usuarioService.buscarPorEmail(usuario, brUsuario);
			
			job.gerarEnviarSenha(usuarioBanco);		
			return ResponseEntity.ok(usuarioBanco);			
		} catch (ValidacaoException | EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
		
	}	
}
