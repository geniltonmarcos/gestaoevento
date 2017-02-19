package com.gestaoevento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.io.output.LockableFileWriter;

import com.gestaoevento.dao.LocalidadeDao;
import com.gestaoevento.dao.imp.LocalidadeDaoImp;
import com.gestaoevento.model.Localidade;
import com.gestaoevento.util.FacesUtils;

@ManagedBean
@RequestScoped
public class LocalidadeBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Localidade localidade;
	private LocalidadeDao localidadeDao;
	private List<Localidade> localidades;
	private FacesUtils facesUtils;
	private ArrayList<SelectItem> localidadesItens;
	
	private boolean alterando; 
		
	public LocalidadeBean() {
		// TODO Auto-generated constructor stub
		this.localidade = new Localidade();
		this.localidadeDao = null;
		this.localidades = null;
		this.facesUtils = new FacesUtils(FacesContext.getCurrentInstance());
		this.alterando = false;
	}
	
	public List<SelectItem> getLocalidadeItens() {
		try {
			list();
			 
			//this.categoriaTitulosItens.add(si);
			localidadesItens = new ArrayList<SelectItem>();
			
			SelectItem si;
			
			for(Localidade localidade : localidades){
				if(localidade.getId() != null && localidade.getAtivo()){
					si = new SelectItem(localidade, localidade.getLocal());
					localidadesItens.add(si); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return localidadesItens;
	}
	
	public void save() {
		
		try {
			localidadeDao = new LocalidadeDaoImp();
			if(localidade.getId() == null || (localidade.getId() != null && localidade.getId() == 0)){
				
				localidadeDao.save(localidade);
			}
			else if(localidade.getId() > 0)
				localidadeDao.update(localidade);
			facesUtils.adicionaMensagemDeInformacao("Dados salvos com Sucesso");
			
			//this.localidade = new Localidade();
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		//return "new";
		
	}
	
	public void delete() {
		try {
			localidadeDao = new LocalidadeDaoImp();
			localidadeDao.delete(localidade);
			list();
			facesUtils.adicionaMensagemDeInformacao("Sucesso no Processamento do Formulário");
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		//return "list";
	}
	
	public String list() {
		try {
			localidade.setLocal(
					FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nome")
			);		
			
			localidadeDao = new LocalidadeDaoImp();
			this.localidades = localidadeDao.list(localidade);
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return "list";
	}
	
	public  List<Localidade> listaDireto(){
		try {
			localidadeDao = new LocalidadeDaoImp();
			return localidadeDao.list(localidade);
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return null;
	}
	
	public String preparaAlteracao() {
		this.alterando = true;
		try {
			String sid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
			//System.out.println("localidade id: "+sid);
			Long id = Long.valueOf(sid);
			localidadeDao = new LocalidadeDaoImp();
			this.localidade = localidadeDao.getLocalidade(id);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		
		return "form";
	}
	
	public String preparaAdicao(){
		localidade = new Localidade();
		return "form";
	}
	
	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public List<Localidade> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(List<Localidade> localidades) {
		this.localidades = localidades;
	}

	public boolean isAlterando() {
		return alterando;
	}

	public void setAlterando(boolean alterando) {
		this.alterando = alterando;
	}
}
