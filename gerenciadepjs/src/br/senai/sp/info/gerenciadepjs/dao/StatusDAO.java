package br.senai.sp.info.gerenciadepjs.dao;

import java.util.List;

import br.senai.sp.info.gerenciadepjs.model.Status;

public interface StatusDAO {

	public void persistir(Status obj);
	
	public Status buscar(Integer id);

	public List<Status> buscarTodos();
	
}
