package br.senai.sp.info.gerenciadepjs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/erros")
public class ErrorController {
	
	@GetMapping("/404")
	public String abrirErro404() {
		return "erros/404";
	}
	
	@GetMapping("/403")
	public String abrirErro403() {
		return "erros/403";
	}
	
	@GetMapping("/401")
	public String abrirErro401() {
		return "erros/401";
	}
	
	@GetMapping("/servidor")
	public String abrirErroServidor() {
		return "erros/servidor";
	}
	
	@GetMapping("/405")
	public String abrirErro405() {
		return "erros/405";
	}
	
}
