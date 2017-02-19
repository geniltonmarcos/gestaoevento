package com.gestaoevento.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.gestaoevento.dao.UsuarioDao;
import com.gestaoevento.dao.imp.UsuarioDaoImp;
import com.gestaoevento.model.Usuario;
import com.gestaoevento.util.FacesUtils;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private FacesUtils facesUtils;
	private boolean loggedIn;

	public LoginBean() {
		this.usuario = new Usuario();
	}

	public String doLogin() {
		try {
			facesUtils = new FacesUtils(FacesContext.getCurrentInstance());
			if (!"".equals(usuario.getSenha()) && !"".equals(usuario.getLogin())) {
				usuarioDao = new UsuarioDaoImp();
				Usuario usu = usuarioDao.login(usuario.getSenha(), usuario.getLogin());
				if (usu != null) {
					loggedIn = true;
					this.usuario = usu;
					return "form";
				} else {
					loggedIn = false;
				}
			}

			facesUtils.adicionaMensagemDeInformacao("Verifique o Login e senha e tente Novamente!");
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return "index";
	}

	public void doLogout() {
		facesUtils = new FacesUtils(FacesContext.getCurrentInstance());
		this.usuario = new Usuario();
		loggedIn = false;
		facesUtils.adicionaMensagemDeInformacao("Loggout realizado com Sucesso!");
		try {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect(context.getRequestContextPath() + "/index.xhtml");
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
