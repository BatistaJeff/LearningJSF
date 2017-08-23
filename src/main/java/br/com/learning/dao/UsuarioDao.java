package br.com.learning.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.learning.modelo.Usuario;

public class UsuarioDao implements Serializable  {

	private static final long serialVersionUID = 1L;

	private DAO<Usuario> dao;

	@Inject
	private EntityManager entityManager;
	

	@PostConstruct
	public void init() {
		
			this.dao = new DAO<Usuario>(this.entityManager, Usuario.class);
	}
	
	public boolean existe(Usuario usuario) {
		
		TypedQuery<Usuario> query = entityManager.createQuery(
				"select u from Usuario u where u.login = :pLogin and u.senha = :pSenha", 
				Usuario.class);

		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		
		try{
			Usuario resultado = new Usuario();
			resultado = query.getSingleResult();
			
		}catch (NoResultException e) {

			return false;
		}
		
		return true;
	}
	
	public void remove(Usuario usuario) {

		dao.remove(usuario);
	}

	public void atualiza(Usuario usuario) {

		dao.atualiza(usuario);
	}

	public void adiciona(Usuario usuario) {

		dao.adiciona(usuario);
	}

	public List<Usuario> listaTodos() {

		return dao.listaTodos();
	}
	
}
