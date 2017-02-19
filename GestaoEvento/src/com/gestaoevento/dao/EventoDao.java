package com.gestaoevento.dao;

import java.util.List;

import com.gestaoevento.model.Evento;

public interface EventoDao {
	public void save(Evento evento);
	public Evento getEvento(Long id);
	public List<Evento> list(Evento evento);
	public List<Evento> listRelatorio(Evento evento);
	public void delete(Evento evento);
	public void update(Evento evento);
}
