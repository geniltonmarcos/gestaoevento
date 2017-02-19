package com.gestaoevento.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.gestaoevento.dao.EventoDao;
import com.gestaoevento.dao.ReservaDao;
import com.gestaoevento.dao.imp.EventoDaoImp;
import com.gestaoevento.dao.imp.ReservaDaoImp;
import com.gestaoevento.model.Evento;
import com.gestaoevento.model.Reserva;
import com.gestaoevento.util.FacesUtils;

@ManagedBean
@RequestScoped
public class ReservaBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Reserva reserva;
	private ReservaDao reservaDao;
	private List<Reserva> reservas;
	private FacesUtils facesUtils;
	private String palavraPesquisa;
	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	private boolean alterando; 
		
	public ReservaBean() {
		// TODO Auto-generated constructor stub
		this.reserva = new Reserva();
		this.reservaDao = null;
		this.reservas = null;
		this.facesUtils = new FacesUtils(FacesContext.getCurrentInstance());
		this.alterando = false;
		this.palavraPesquisa = "";
	}
	
	public void save() {
		
		try {
			reservaDao = new ReservaDaoImp();
			reserva.setUsuario(loginBean.getUsuario());
			if(reserva.getId() == null || (reserva.getId() != null && reserva.getId() == 0)){
				reserva.setStatus("A");
				reservaDao.save(reserva);
				
				EventoDao evtDao = new EventoDaoImp();
				Evento evt = reserva.getEvento();
				evt.setStatus("S");
				evtDao.save(evt);
			}
			else if(reserva.getId() > 0)
				reservaDao.update(reserva);
			facesUtils.adicionaMensagemDeInformacao("Dados salvos com Sucesso");
			
			//this.reserva = new Reserva();
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		//return "new";
		
	}
	
	public void delete() {
		try {
			reservaDao = new ReservaDaoImp();
			reservaDao.delete(reserva);
			list();
			facesUtils.adicionaMensagemDeInformacao("Sucesso no Processamento do Formulário");
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		//return "list";
	}
	
	public String list() {
		try {
			reserva.setStatus(
					FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("status")
			);	
			
			reservaDao = new ReservaDaoImp();
			this.reservas = reservaDao.list(reserva, 
					loginBean.getUsuario().getId(), 
					loginBean.getUsuario().getTipo());
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return "list";
	}
	
	public  List<Reserva> listaDireto(){
		try {
			reservaDao = new ReservaDaoImp();
			return reservaDao.list(reserva, 
					loginBean.getUsuario().getId(), 
					loginBean.getUsuario().getTipo());
			
		} catch (Exception e) {
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		return null;
	}
	
	public String preparaAlteracao() {
		this.alterando = true;
		try {
			String sid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
			//System.out.println("reserva id: "+sid);
			Long id = Long.valueOf(sid);
			reservaDao = new ReservaDaoImp();
			this.reserva = reservaDao.getReserva(id);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			facesUtils.adicionaMensagemDeInformacao(e.getLocalizedMessage());
		}
		
		return "form";
	}
	
	public String preparaAdicao(){
		reserva = new Reserva();
		return "form";
	}
	
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
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

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	
	
}
