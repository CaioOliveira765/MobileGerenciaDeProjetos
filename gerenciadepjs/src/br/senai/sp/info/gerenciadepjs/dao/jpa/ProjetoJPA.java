package br.senai.sp.info.gerenciadepjs.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.gerenciadepjs.dao.ProjetoDAO;
import br.senai.sp.info.gerenciadepjs.model.Projeto;
import br.senai.sp.info.gerenciadepjs.model.Status;
import br.senai.sp.info.gerenciadepjs.model.Tecnologia;

@Repository
@Transactional
public class ProjetoJPA implements ProjetoDAO {
	
	@Autowired
	private SessionFactory sessionFac;

	@Override
	public void persistir(Projeto obj) {
		sessionFac.getCurrentSession().persist(obj);
		
	}

	@Override
	public void deletar(Projeto obj) {
		sessionFac.getCurrentSession().delete(obj);
		
	}

	@Override
	public void alterar(Projeto obj) {
		sessionFac.getCurrentSession().update(obj);
		
	}

	@Override
	public List<Projeto> buscarTodos() {
		String hql = "FROM Projeto p";
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public Projeto buscar(Long id) {
		String hql = "FROM Projeto p WHERE p.id = :id";
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		List<Projeto> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Projeto buscarPorNome(String nome) {	
		String hql = "FROM Projeto p WHERE p.nome = :nome";	
		Query query  = sessionFac.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		
		List<Projeto> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Projeto> buscarPorTecnologia(Long id) {
		String hql = "FROM Projeto p WHERE p.tecnologia.id = :id";
		Query query  = sessionFac.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
				
		return query.list();
		
	}

	@Override
	public List<Projeto> buscarPorStatus(Integer statusId) {
		
		String hql = "FROM Projeto p WHERE status.id = :id";
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		query.setParameter("id", statusId);;
		return query.list();
	}

	@Override
	public List<Projeto> pesquisarPorNome(String nome) {
		String hql = "FROM Projeto p WHERE p.nome LIKE :nome OR p.id LIKE :nome OR p.responsavelBRQ LIKE :nome";
		
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		query.setParameter("nome", "%"+nome+"%");
		
		return query.list();
	}
}
