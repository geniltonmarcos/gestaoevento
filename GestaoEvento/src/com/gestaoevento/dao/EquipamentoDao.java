package com.gestaoevento.dao;

import java.util.List;

import com.gestaoevento.model.Equipamento;

public interface EquipamentoDao {
	public void save(Equipamento equipamento);
	public Equipamento getEquipamento(Long id);
	public List<Equipamento> list(Equipamento equipamento);
	public void delete(Equipamento equipamento);
	public void update(Equipamento equipamento);
}
