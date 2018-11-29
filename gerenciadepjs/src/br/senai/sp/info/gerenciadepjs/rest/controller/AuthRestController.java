package br.senai.sp.info.gerenciadepjs.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.gerenciadepjs.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.gerenciadepjs.exceptions.ValidacaoException;
import br.senai.sp.info.gerenciadepjs.model.Usuario;
import br.senai.sp.info.gerenciadepjs.service.UsuarioService;
import br.senai.sp.info.gerenciadepjs.utils.JwtUtils;
import br.senai.sp.info.gerenciadepjs.utils.MapUtils;

@RestController
@RequestMapping("/rest/auth")
public class AuthRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/jwt")
	public ResponseEntity<Object> gerarJwt(@Valid @RequestBody Usuario usuario, BindingResult br){
		try {
			//200
			Usuario usuarioBuscado = usuarioService.buscarPorEmailESenha(usuario, br);
			Map<String, String> mapaToken = new HashMap<>();
			mapaToken.put("token", JwtUtils.gerarToken(usuarioBuscado));
			return ResponseEntity
						.ok(mapaToken);//mapaToken
		}catch(ValidacaoException e) {
			//422
			return ResponseEntity
					.unprocessableEntity()
					.body(MapUtils.mapaDe(br));
		} catch (EntidadeNaoEncontradaException e) {
			// 404
			return ResponseEntity
						.notFound()
						.header("X-Reason", "Entidade não encontrada")
						.build();
		} catch (Exception e) {
			// 500
			return ResponseEntity
						.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.build();
		}
	}
}
