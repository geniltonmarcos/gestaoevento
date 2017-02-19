package com.gestaoevento.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.gestaoevento.dao.LocalidadeDao;
import com.gestaoevento.dao.imp.LocalidadeDaoImp;
import com.gestaoevento.model.Localidade;


@FacesConverter(value = "localidadeConverter", forClass = Localidade.class)
public class LocalidadeConverter implements Converter {

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		if(object instanceof Localidade){
		Localidade automovel = (Localidade) object;
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
		LocalidadeDao dao = new LocalidadeDaoImp();
		Localidade automovel = dao.getLocalidade(id);//entityManager.find(Automovel.class, id);
		return automovel;
	}

}
