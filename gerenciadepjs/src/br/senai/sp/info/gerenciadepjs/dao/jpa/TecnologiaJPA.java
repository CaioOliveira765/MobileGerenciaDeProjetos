package br.senai.sp.info.gerenciadepjs.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.gerenciadepjs.dao.TecnologiaDAO;
import br.senai.sp.info.gerenciadepjs.model.Tecnologia;
import br.senai.sp.info.gerenciadepjs.model.Usuario;

@Repository
@Transactional
public class TecnologiaJPA implements TecnologiaDAO{

	@Autowired
	private SessionFactory sessionFac;
	
	@Override
	public void persistir(Tecnologia obj) {
		sessionFac.getCurrentSession().persist(obj);	
	}

	@Override
	public void deletar(Tecnologia obj) {
		sessionFac.getCurrentSession().delete(obj);		
	}

	@Override
	public void alterar(Tecnologia obj) {
		sessionFac.getCurrentSession().update(obj);	
	}

	@Override
	public List<Tecnologia> buscarTodos() {
		String hql = "FROM Tecnologia t";
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public Tecnologia buscar(Long id) {
		String hql = "FROM Tecnologia t WHERE t.id = :id";
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		List<Tecnologia> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Tecnologia buscarPorNome(String nome) {
		String hql = "FROM Tecnologia t WHERE t.nome = :nome";
		
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		
		List<Tecnologia> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}	
	}

	@Override
	public List<Tecnologia> pesquisarPorNome(String nome) {
		String hql = "FROM Tecnologia t WHERE t.nome LIKE :nome";
		
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		query.setParameter("nome", "%"+nome+"%");
		
		return query.list();
	}
	
}
