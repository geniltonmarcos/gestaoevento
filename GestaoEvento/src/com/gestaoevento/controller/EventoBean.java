package com.gestaoevento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.gestaoevento.dao.EventoDao;
import com.gestaoevento.dao.imp.EventoDaoImp;
import com.gestaoevento.model.Evento;
import com.gestaoevento.util.FacesUtils;

@ManagedBean
@RequestScoped
public class EventoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Evento evento;
	private EventoDao eventoDao;
	private List<Evento> eventos;
	private FacesUtils facesUtils;
	private ArrayList<SelectItem> eventosItens;
	
	private boolean alterando; 
		
	public EventoBean() {
		// TODO Auto-generated constructor stub
		this.evento = new Evento();
		this.eventoDao = null;
		this.eventos = null;
		this.facesUtils = new FacesUtils(FacesContext.getCurrentInstance());
		this.alterando = false;
		this.eventosItens = null;
	}
	
	public List<SelectItem> getEventoItens() {
		try {
			list();
			 
			//this.categoriaTitulosItens.add(si);
			eventosItens = new ArrayList<SelectItem>();
			
			SelectItem si;// = new SelectItem(""," ");
			//categoriaTitulosItens.add(si);
			
			for(Evento evento : eventos){
				if(evento.getId() != null){
					si = new SelectItem(evento, evento.getDescricao());
					eventosItens.add(si); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return eventosItens;
	}
	
	public void save() {
		
		try {
			eventoDao = new EventoDaoImp();
			if(evento.getId() == null || (evento.getId() != null && evento.getId() == 0)){
				evento.setStatus("N");
				eventoDao.save(evento);
			}
			else if(evento.getId() > 0)
				eventoDao.update(evento);
			facesUtils.adicionaMensagemDeInformacao("Dados salvos com Sucesso");
			
			//this.evento = new Evento();
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		//return "new";
		
	}
	
	public void delete() {
		try {
			eventoDao = new EventoDaoImp();
			eventoDao.delete(evento);
			list();
			facesUtils.adicionaMensagemDeInformacao("Sucesso no Processamento do Formulário");
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		//return "list";
	}
	
	public String list() {
		try {
			evento.setStatus(
					FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("status")
			);		
			
			eventoDao = new EventoDaoImp();
			this.eventos = eventoDao.list(evento);
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return "list";
	}
	
	public String listRelatorio() {
		try {
			evento.setStatus(
					FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("status")
			);			
			
			eventoDao = new EventoDaoImp();
			eventos = eventoDao.list(evento);
//			eventos = new ArrayList<Evento>();
//			
//			for (Evento evento : evts) {
//				if(evento.getStatus() != null){
//					if(evento.getStatus().equals("N"))
//						evento.setStatus("Novo");
//					else if(evento.getStatus().equals("S"))
//						evento.setStatus("Solicitado");
//					else if(evento.getStatus().equals("A"))
//						evento.setStatus("Autorizado");
//				}
//				eventos.add(evento);
//			}
			
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return "list";
	}
	
	public  List<Evento> listaDireto(){
		try {
			eventoDao = new EventoDaoImp();
			return eventoDao.list(evento);
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return null;
	}
	
	public String preparaAlteracao() {
		this.alterando = true;
		try {
			String sid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
			//System.out.println("evento id: "+sid);
			Long id = Long.valueOf(sid);
			eventoDao = new EventoDaoImp();
			this.evento = eventoDao.getEvento(id);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		
		return "form";
	}
	
	public String preparaAdicao(){
		evento = new Evento();
		return "form";
	}
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public boolean isAlterando() {
		return alterando;
	}

	public void setAlterando(boolean alterando) {
		this.alterando = alterando;
	}
}
