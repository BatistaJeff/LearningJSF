package br.com.learning.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.learning.modelo.Livro;

public class LivroDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;
	
	private DAO<Livro> dao;
	
	@PostConstruct
	public void init() {
		
		this.dao = new DAO<Livro>(this.entityManager, Livro.class);
	}
	
	
	public List<Livro> listaTodos() {

		return dao.listaTodos();
	}

	public Livro buscaPorId(Integer livroId) {

		return dao.buscaPorId(livroId);
	}

	public void remove(Livro livro) {

		dao.remove(livro);
	}

	public void adiciona(Livro livro) {

		dao.adiciona(livro);
	}

	public void atualiza(Livro livro) {

		dao.atualiza(livro);
	}

	public int contaTodos() {
		
		long result = (Long) entityManager.createQuery("select count(n) from livro n").getSingleResult();
		
		return (int) result;
	}
	
}
