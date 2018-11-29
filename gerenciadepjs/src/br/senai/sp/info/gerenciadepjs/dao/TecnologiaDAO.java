package br.senai.sp.info.gerenciadepjs.dao;

import java.util.List;

import br.senai.sp.info.gerenciadepjs.model.Tecnologia;

public interface TecnologiaDAO {

    public void persistir(Tecnologia obj);
	
	public void deletar(Tecnologia obj);
	
	public void alterar(Tecnologia obj);
	
	public List<Tecnologia> buscarTodos();
	
	public Tecnologia buscar(Long id);
	
	public Tecnologia buscarPorNome(String nome);

	public List<Tecnologia> pesquisarPorNome(String nome);
	
}
