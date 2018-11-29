package br.senai.sp.info.gerenciadepjs.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.gerenciadepjs.dao.StatusDAO;
import br.senai.sp.info.gerenciadepjs.model.Status;

@Repository
@Transactional
public class StatusJPA implements StatusDAO{

	@Autowired
	private SessionFactory sessionFac;
	
	@Override
	public Status buscar(Integer id) {
		String hql = "FROM Status s WHERE s.id = :id";
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		List<Status> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Status> buscarTodos() {
		String hql = "FROM Status s";
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public void persistir(Status obj) {
		sessionFac.getCurrentSession().persist(obj);
		
	}
	
}
