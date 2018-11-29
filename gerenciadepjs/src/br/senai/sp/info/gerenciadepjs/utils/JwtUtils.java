package br.senai.sp.info.gerenciadepjs.utils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.senai.sp.info.gerenciadepjs.model.Permissao;
import br.senai.sp.info.gerenciadepjs.model.Usuario;

public class JwtUtils {
	
	public static String gerarToken(Usuario usuario) throws IllegalArgumentException,
															JWTCreationException,
															UnsupportedEncodingException{
		
		Calendar  calendarExpiracao = Calendar.getInstance();
		calendarExpiracao.add(Calendar.SECOND, 60);
		//Date dataExpiracao = calendarExpiracao.getTime();
		
		return JWT.create().withIssuer("BRQ - Gerencia de projetos")
						    .withIssuedAt(new Date())
						    .withSubject("Authentication")
						    .withClaim("id", usuario.getId())
						    .withClaim("nome", usuario.getNome())
						    .withClaim("tipo", usuario.getPermissao().toString())
						                          //ALGORITMO DE CRIPTOGRAFIA AQUI
						    .sign(Algorithm.HMAC512("AUED87B7CB7UD893DBEWEDK9KD3389CBHEDEJI"));
	}
	
	public static Usuario extrairUsuarioDoToken(String token) {
		
		Usuario usuario = new Usuario();
		
		DecodedJWT jwtDecodificado = JWT.decode(token);
		usuario.setId(jwtDecodificado.getClaim("id").asLong());
		usuario.setNome(jwtDecodificado.getClaim("nome").asString());
		usuario.setPermissao(Permissao.valueOf(jwtDecodificado.getClaim("permissao").asString()));
		
		return usuario;
	}
	
	public static void validarToken(String token) throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException{
                                    //ALGORITMO DE CRIPTOGRAFIA IAQUI TBM		
		JWT.require(Algorithm.HMAC512("AUED87B7CB7UD893DBEWEDK9KD3389CBHEDEJI")).build().verify(token);
	}

}
