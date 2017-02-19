package com.gestaoevento.dao;

import java.util.List;

import com.gestaoevento.model.Localidade;

public interface LocalidadeDao {
	public void save(Localidade localidade);
	public Localidade getLocalidade(Long id);
	public List<Localidade> list(Localidade localidade);
	public void delete(Localidade localidade);
	public void update(Localidade localidade);
}
