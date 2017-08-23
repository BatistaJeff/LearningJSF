package br.com.learning.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.learning.dao.UsuarioDao;
import br.com.learning.modelo.Usuario;
import br.com.learning.util.Paginas;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private FacesContext context;
	
	public void efetuarLogin() {
		
		boolean existe = usuarioDao.existe(this.usuario);
		
		if(existe) {
		
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			
			Paginas.livro();
		}
		else {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Usuário não encontrado"));
			
		}
	}

	public Usuario getUsuario() {
		if(this.usuario == null){
			this.usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
