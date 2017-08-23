package br.com.learning.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private final Class<T> classe;
	
	private EntityManager entityManager;

	public DAO(EntityManager em, Class<T> classe) {
		
		this.entityManager = em;
		this.classe = classe;
	}

	public void adiciona(T t) {

		entityManager.persist(t);
	}

	public void remove(T t) {
		
		entityManager.remove(entityManager.merge(t));
	}

	public void atualiza(T t) {
		
		entityManager.merge(t);
	}

	public List<T> listaTodos() {
		
		CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(classe);
		
		query.select(query.from(classe));

		List<T> lista = entityManager.createQuery(query).getResultList();

		return lista;
	}

	public T buscaPorId(Integer id) {
		
		T instancia = entityManager.find(classe, id);
		
		return instancia;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		
		CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(classe);
		
		query.select(query.from(classe));

		List<T> lista = entityManager.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

		return lista;
	}

}
