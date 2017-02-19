package com.gestaoevento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.gestaoevento.dao.EquipamentoDao;
import com.gestaoevento.dao.imp.EquipamentoDaoImp;
import com.gestaoevento.model.Equipamento;
import com.gestaoevento.util.FacesUtils;

@ManagedBean
@RequestScoped
public class EquipamentoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Equipamento equipamento;
	private EquipamentoDao equipamentoDao;
	private List<Equipamento> equipamentos;
	private FacesUtils facesUtils;
	
	private boolean alterando; 
		
	public EquipamentoBean() {
		// TODO Auto-generated constructor stub
		this.equipamento = new Equipamento();
		this.equipamentoDao = null;
		this.equipamentos = null;
		this.facesUtils = new FacesUtils(FacesContext.getCurrentInstance());
		this.alterando = false;
	}
	
	public void save() {
		
		try {
			equipamentoDao = new EquipamentoDaoImp();
			if(equipamento.getId() == null || (equipamento.getId() != null && equipamento.getId() == 0)){
				
				equipamentoDao.save(equipamento);
			}
			else if(equipamento.getId() > 0)
				equipamentoDao.update(equipamento);
			facesUtils.adicionaMensagemDeInformacao("Dados salvos com Sucesso");
			
			//this.equipamento = new Equipamento();
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		//return "new";
		
	}
	
	public void delete() {
		try {
			equipamentoDao = new EquipamentoDaoImp();
			equipamentoDao.delete(equipamento);
			list();
			facesUtils.adicionaMensagemDeInformacao("Sucesso no Processamento do Formulário");
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		//return "list";
	}
	
	public String list() {
		try {
			equipamento.setNome(
					FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nome")
			);		
			
			equipamentoDao = new EquipamentoDaoImp();
			this.equipamentos = equipamentoDao.list(equipamento);
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return "list";
	}
	
	public  List<Equipamento> listaDireto(){
		try {
			equipamentoDao = new EquipamentoDaoImp();
			List<Equipamento> equis = equipamentoDao.list(equipamento);
			List<Equipamento> result = new ArrayList<Equipamento>();
			for (Equipamento equipamento : equis) {
				if(equipamento.getStatus())
					result.add(equipamento);
			}
			
			return result;
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return null;
	}
	
	public String preparaAlteracao() {
		this.alterando = true;
		try {
			String sid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
			//System.out.println("equipamento id: "+sid);
			Long id = Long.valueOf(sid);
			equipamentoDao = new EquipamentoDaoImp();
			this.equipamento = equipamentoDao.getEquipamento(id);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		
		return "form";
	}
	
	public String preparaAdicao(){
		equipamento = new Equipamento();
		return "form";
	}
	
	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public boolean isAlterando() {
		return alterando;
	}

	public void setAlterando(boolean alterando) {
		this.alterando = alterando;
	}
}
