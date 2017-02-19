package com.gestaoevento.dao;

import java.util.List;

import com.gestaoevento.model.Reserva;

public interface ReservaDao {
	public void save(Reserva reserva);
	public Reserva getReserva(Long id);
	public List<Reserva> list(Reserva reserva, Long idUsuario, String tipoUsuario);
	public void delete(Reserva reserva);
	public void update(Reserva reserva);
}
