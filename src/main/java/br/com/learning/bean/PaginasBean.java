package br.com.learning.bean;

import java.io.Serializable;

import javax.inject.Named;

import br.com.learning.util.Paginas;

@Named
public class PaginasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public void login() {
		Paginas.login();
	}
	
	public void autor() {
		Paginas.autor();
	}
	
	public void livro() {
		Paginas.livro();;
	}
	
	public void usuario() {
		Paginas.usuario();
	}
}
