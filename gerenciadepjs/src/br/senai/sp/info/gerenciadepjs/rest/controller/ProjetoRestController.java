package br.senai.sp.info.gerenciadepjs.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.gerenciadepjs.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.gerenciadepjs.service.ProjetoService;

@RestController
@RequestMapping("/rest/projetos")
public class ProjetoRestController {
	
	@Autowired
	private ProjetoService projetoService;
		
	@GetMapping
	public ResponseEntity<Object> buscarTodos(){
		try {
			return ResponseEntity.ok(projetoService.buscarTodos());
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id){
		try {
			return ResponseEntity.ok(projetoService.buscar(id));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	} 
	
	@GetMapping("/tec{idTecnologia}")
	public ResponseEntity<Object> buscarPorTecnologia(@PathVariable Long idTecnologia){
		try {
			return ResponseEntity.ok(projetoService.buscarPorTecnologia(idTecnologia));
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	} 
	
	@GetMapping("/pesquisa{nome}")
	public ResponseEntity<Object> pequisarPorNome(@PathVariable String nome){
		try {
			return ResponseEntity.ok(projetoService.pesquisarPorNome(nome));
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}	
	}
}
