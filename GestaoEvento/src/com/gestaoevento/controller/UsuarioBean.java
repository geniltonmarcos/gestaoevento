package com.gestaoevento.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.gestaoevento.dao.UsuarioDao;
import com.gestaoevento.dao.imp.UsuarioDaoImp;
import com.gestaoevento.model.Usuario;
import com.gestaoevento.util.FacesUtils;

@ManagedBean
@RequestScoped
public class UsuarioBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private List<Usuario> usuarios;
	private FacesUtils facesUtils;
	private String palavraPesquisa;
	
	private boolean alterando; 
		
	public UsuarioBean() {
		// TODO Auto-generated constructor stub
		this.usuario = new Usuario();
		this.usuarioDao = null;
		this.usuarios = null;
		this.facesUtils = new FacesUtils(FacesContext.getCurrentInstance());
		this.alterando = false;
		this.palavraPesquisa = "";
	}
	
	public void save() {
		
		try {
			usuarioDao = new UsuarioDaoImp();
			if(usuario.getId() == null || (usuario.getId() != null && usuario.getId() == 0)){
				usuarioDao.save(usuario);
			}
			else if(usuario.getId() > 0)
				usuarioDao.update(usuario);
			facesUtils.adicionaMensagemDeInformacao("Dados salvos com Sucesso");
			
			//this.usuario = new Usuario();
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		//return "new";
		
	}
	
	public void delete() {
		try {
			usuarioDao = new UsuarioDaoImp();
			if(usuarioDao.delete(usuario))
				facesUtils.adicionaMensagemDeInformacao("Sucesso no Processamento do Formulário");
			else 
				facesUtils.adicionaMensagemDeInformacao("Usuário com reservas já vinculadas.");
			
			list();
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		//return "list";
	}
	
	public String list() {
		try {
			usuario.setNome(
					FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nome")
			);		
			
			usuarioDao = new UsuarioDaoImp();
			this.usuarios = usuarioDao.list(usuario);
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return "list";
	}
	
	public  List<Usuario> listaDireto(){
		try {
			usuarioDao = new UsuarioDaoImp();
			return usuarioDao.list(usuario);
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return null;
	}
	
	public String preparaAlteracao() {
		this.alterando = true;
		try {
			String sid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
			//System.out.println("usuario id: "+sid);
			Long id = Long.valueOf(sid);
			usuarioDao = new UsuarioDaoImp();
			this.usuario = usuarioDao.getUsuario(id);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		
		return "form";
	}
	
	public String preparaAdicao(){
		usuario = new Usuario();
		return "form";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isAlterando() {
		return alterando;
	}

	public void setAlterando(boolean alterando) {
		this.alterando = alterando;
	}

	public String getPalavraPesquisa() {
		return palavraPesquisa;
	}

	public void setPalavraPesquisa(String palavraPesquisa) {
		this.palavraPesquisa = palavraPesquisa;
	}
	
	
	
}
