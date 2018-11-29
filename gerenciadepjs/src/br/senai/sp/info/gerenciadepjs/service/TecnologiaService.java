package br.senai.sp.info.gerenciadepjs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.info.gerenciadepjs.dao.TecnologiaDAO;
import br.senai.sp.info.gerenciadepjs.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.gerenciadepjs.model.Tecnologia;

@Service
public class TecnologiaService {

	@Autowired
	private TecnologiaDAO dao;
	
	public List<Tecnologia> buscarTodos(){
		return dao.buscarTodos();
	}
	
	public Tecnologia buscar(Long id) throws EntidadeNaoEncontradaException{
		Tecnologia t = dao.buscar(id);
		
		if(t == null) {
			throw new EntidadeNaoEncontradaException();
		}	
		return t;
	}
	
	public List<Tecnologia> pesquisarPorNome(String nome){
		return dao.pesquisarPorNome(nome);
	}
}
