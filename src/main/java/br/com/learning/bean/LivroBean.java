package br.com.learning.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.learning.dao.AutorDao;
import br.com.learning.dao.LivroDao;
import br.com.learning.modelo.Autor;
import br.com.learning.modelo.Livro;
import br.com.learning.tx.Transacional;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 8421363674985464222L;

	@Inject
	private LivroDao livroDao;

	@Inject
	private AutorDao autorDao;
	
	@Inject
	private FacesContext context;
	
	private Livro livro;

	private List<Livro> livros;
	
	private Integer autorId;

	public void removerAutorDoLivro(Autor autor) {
		
		this.livro.removeAutor(autor);
	}
	
	public List<Autor> getAutoresDoLivro() {
		
		return this.livro.getAutores();
	}

	public void gravarAutor() {
		
		Autor autor = autorDao.buscaPorId(this.autorId);
		
		this.livro.adicionaAutor(autor);
	}
	
	@Transacional
	public void removerLivro(Livro livro) {
		
		livroDao.remove(livro);
		
		getLivros().remove(livro);
	}
	
	public void carregarLivroParaEdicao(Livro livro) {
		
		this.livro = livro;
	}
	
	@Transacional
	public void gravar() throws ValidatorException{

		if (this.livro.getAutores().isEmpty()) {
		 
			context.addMessage(null, new FacesMessage("Livro deve ter pelo menos 1 autor"));
			return;
		}

		if(this.livro.getId() == null) {

			livroDao.adiciona(this.livro);
			this.livros = livroDao.listaTodos();
		}
		else {
			livroDao.atualiza(this.livro);
		}
		this.livro = new Livro();
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) 
			throws ValidatorException {

		String valor = value.toString();
		
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deveria começar com 1"));
		}
	}
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public Livro getLivro() {
		if(this.livro == null) {
			this.livro = new Livro();
		}
		return livro;
	}

	public List<Livro> getLivros() {
		
		if(this.livros == null) {
			
			this.livros = livroDao.listaTodos();
		}
		
		return livros;
	}

	public List<Autor> getAutores() {
		
		return autorDao.listaTodos();
	}
}
