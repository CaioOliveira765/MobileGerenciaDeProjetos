package br.senai.sp.info.gerenciadepjs.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class MapUtils {

	public static Map<String, String> mapaDe(BindingResult bindingResult){
		//Criar um mapa para armazenar os erros do binding result
		Map<String, String> mapaErros = new HashMap<>();
		
		//Pegar cada erro do binding result e aplica-lo no mapa
		for (FieldError erro : bindingResult.getFieldErrors()) {
			mapaErros.put(erro.getField(), erro.getDefaultMessage());
		}		
		return mapaErros;
	}
	
}
