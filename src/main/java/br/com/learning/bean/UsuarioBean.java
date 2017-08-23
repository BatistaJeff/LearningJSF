package br.com.learning.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.learning.dao.UsuarioDao;
import br.com.learning.modelo.Usuario;
import br.com.learning.tx.Transacional;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDao usuarioDao;
	
	private Usuario usuario;

	public void carregarUsuarioParaEdicao(Usuario usuario) {
		
		this.usuario = usuario;
	}
	
	@Transacional
	public void removerUsuario(Usuario usuario) {

		usuarioDao.remove(usuario);
	}
	
	@Transacional
	public void gravar() {
		
		if(this.usuario != null) {
			
			if(this.usuario.getId() != null) {
				
				usuarioDao.atualiza(this.usuario);
			}
			else {
				
				usuarioDao.adiciona(this.usuario);
			}
		}
		
		this.usuario = new Usuario(); 
	}
	
	public List<Usuario> getUsuarios() {
		
		List<Usuario> todosUsuario = usuarioDao.listaTodos();
		
		return todosUsuario;
	}
	
	public Usuario getUsuario() {
		if(this.usuario == null) {
			this.usuario = new Usuario();
		}
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
	
}
