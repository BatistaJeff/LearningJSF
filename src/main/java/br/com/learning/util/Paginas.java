package br.com.learning.util;

import java.io.IOException;

import javax.faces.context.FacesContext;

public class Paginas {

	public static void livro() {
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("livro.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void autor() {
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("autor.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void usuario() {
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("usuario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void login() {
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
