package br.senai.sp.info.gerenciadepjs.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.gerenciadepjs.dao.UsuarioDAO;
import br.senai.sp.info.gerenciadepjs.model.Usuario;

@Repository
@Transactional
public class UsuarioJPA implements UsuarioDAO{

	@Autowired
	private SessionFactory sessionFac;
	
	@Override
	public void persistir(Usuario obj) {
		sessionFac.getCurrentSession().persist(obj);
		
	}

	@Override
	public void deletar(Usuario obj) {
		sessionFac.getCurrentSession().delete(obj);
		
	}

	@Override
	public void alterar(Usuario obj) {
		sessionFac.getCurrentSession().update(obj);
		
	}

	@Override
	public List<Usuario> buscarTodos() {
		
		String hql = "FROM Usuario u";
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public Usuario buscar(Long id) {
		String hql = "FROM Usuario u WHERE u.id = :id";
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		List<Usuario> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Usuario buscarPorNomeSobrenome(String nome, String sobrenome) {
		String hql = "FROM Usuario u WHERE u.nome LIKE :nome";
		return null;
	}

	@Override
	public Usuario buscarPorEmail(String email) {
		String hql = "FROM Usuario u WHERE u.email = :email";
		
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		
		List<Usuario> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Usuario buscarPorEmailESenha(String email, String senha) {
		String hql = "FROM Usuario u WHERE u.email = :email AND u.senha = :senha";
		
		Query query = sessionFac.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		
		List<Usuario> resultado = query.list();
		
		if(!resultado.isEmpty()) {
			return resultado.get(0);
		}else {
			return null;
		}
	}

}
