package br.senai.sp.info.gerenciadepjs.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.senai.sp.info.gerenciadepjs.dao.StatusDAO;
import br.senai.sp.info.gerenciadepjs.model.Status;

@Component
public class CadastrarStatusJob implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private StatusDAO dao;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent e) {
		
		Status iniciado = new Status();
		Status emAndamento = new Status();
		Status finalizado = new Status();
		
		if(dao.buscar(0) == null) {		
			System.out.println("[Jobs]: Cadastrando status: iniciado");
			iniciado.setId(0);
			iniciado.setNome("Iniciado");
			dao.persistir(iniciado);
		}
		
		if(dao.buscar(1) == null) {		
			System.out.println("[Jobs]: Cadastrando status: em andamento");
			emAndamento.setId(1);
			emAndamento.setNome("Em andamento");
			dao.persistir(emAndamento);
		}
		
		if(dao.buscar(2) == null) {		
			System.out.println("[Jobs]: Cadastrando status: finalizado");;
			finalizado.setId(2);
			finalizado.setNome("Finalizado");
			dao.persistir(finalizado);
		}
	}
}
