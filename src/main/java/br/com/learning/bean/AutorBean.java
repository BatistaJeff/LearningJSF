package br.com.learning.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.learning.dao.AutorDao;
import br.com.learning.modelo.Autor;
import br.com.learning.tx.Transacional;

@Named			//javax.inject.Named
@ViewScoped 	//javax.faces.view.ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 5155564736620122900L;

	private Autor autor = new Autor();

	@Inject
	private AutorDao autorDao;
	
	@Inject
	private FacesContext context;
	
	@Transacional
	public void gravar() {
		
		if(this.autor.getId() == null) {
			
			autorDao.adiciona(this.autor);
		}
		else {

			autorDao.atualiza(this.autor);	
		}
		
		this.autor = new Autor();
	}
	
	public void carregarAutorParaEdicao(Autor autor) {
		this.autor = autor;
	}
	
	@Transacional
	public void excluirAutor(Autor autorPagina) {
		
		try {
			autorDao.remove(autorPagina);
		}catch (Exception e) {
			
			context.addMessage(null, new FacesMessage("Não é possivel excluir autor! Existe livro para este autor"));
		}
	}
	
	public List<Autor> getAutores() {
		
		List<Autor> listaTodos = autorDao.listaTodos();
		
		return listaTodos;
	}
	
	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
