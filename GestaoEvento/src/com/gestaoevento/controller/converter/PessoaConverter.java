package com.gestaoevento.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.gestaoevento.dao.PessoaDao;
import com.gestaoevento.dao.imp.PessoaDaoImp;
import com.gestaoevento.model.Pessoa;


@FacesConverter(value = "pessoaConverter", forClass = Pessoa.class)
public class PessoaConverter implements Converter {

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		if(object instanceof Pessoa){
		Pessoa automovel = (Pessoa) object;
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
		PessoaDao dao = new PessoaDaoImp();
		Pessoa automovel = dao.getPessoa(id);//entityManager.find(Automovel.class, id);
		return automovel;
	}

}
