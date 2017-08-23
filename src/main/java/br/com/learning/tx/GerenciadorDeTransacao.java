package br.com.learning.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager entityManager;
	
	@AroundInvoke
	public Object executaTX(InvocationContext context) throws Exception {
		
		entityManager.getTransaction().begin();
		
		Object result = context.proceed();
		
		entityManager.getTransaction().commit();
	
		return result;
	}
	
}
