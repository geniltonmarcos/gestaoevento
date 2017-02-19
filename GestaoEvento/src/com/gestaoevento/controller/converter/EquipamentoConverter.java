package com.gestaoevento.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.gestaoevento.dao.EquipamentoDao;
import com.gestaoevento.dao.imp.EquipamentoDaoImp;
import com.gestaoevento.model.Equipamento;


@FacesConverter(value = "equipamentoConverter", forClass = Equipamento.class)
public class EquipamentoConverter implements Converter {

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		if(object instanceof Equipamento){
		Equipamento automovel = (Equipamento) object;
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
		EquipamentoDao dao = new EquipamentoDaoImp();
		Equipamento automovel = dao.getEquipamento(id);//entityManager.find(Automovel.class, id);
		return automovel;
	}

}
