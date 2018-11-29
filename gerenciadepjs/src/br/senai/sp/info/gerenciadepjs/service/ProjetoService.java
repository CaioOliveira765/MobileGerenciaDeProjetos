package br.senai.sp.info.gerenciadepjs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.info.gerenciadepjs.dao.ProjetoDAO;
import br.senai.sp.info.gerenciadepjs.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.gerenciadepjs.model.Projeto;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoDAO dao;
	
	public List<Projeto> buscarTodos(){
		return dao.buscarTodos();
	}
	
	public Projeto buscar(Long id) throws EntidadeNaoEncontradaException{
		Projeto p = dao.buscar(id);
		
		if(p == null) {
			throw new EntidadeNaoEncontradaException();
		}
		return p;
	}
	
	public List<Projeto> pesquisarPorNome(String nome){
		return dao.pesquisarPorNome(nome);
	}
	
	public List<Projeto> buscarPorTecnologia(Long idTecnologia) {		
		return dao.buscarPorTecnologia(idTecnologia);			
	}	
}
