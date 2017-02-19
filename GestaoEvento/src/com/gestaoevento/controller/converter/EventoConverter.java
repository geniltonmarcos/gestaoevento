package com.gestaoevento.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.gestaoevento.dao.EventoDao;
import com.gestaoevento.dao.imp.EventoDaoImp;
import com.gestaoevento.model.Evento;


@FacesConverter(value = "eventoConverter", forClass = Evento.class)
public class EventoConverter implements Converter {

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		if(object instanceof Evento){
		Evento automovel = (Evento) object;
		if (automovel == null || automovel.getId() == null)
			return null;
		return String.valueOf(automovel.getId());
		}
		return null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Long id = Long.valueOf(string);
		EventoDao dao = new EventoDaoImp();
		Evento automovel = dao.getEvento(id);//entityManager.find(Automovel.class, id);
		return automovel;
	}

}
