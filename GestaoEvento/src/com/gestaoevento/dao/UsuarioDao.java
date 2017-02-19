package com.gestaoevento.dao;

import java.util.List;

import com.gestaoevento.model.Usuario;

public interface UsuarioDao {
	public void save(Usuario usuario);
	public Usuario getUsuario(Long id);
	public List<Usuario> list(Usuario usuario);
	public Boolean delete(Usuario usuario);
	public void update(Usuario usuario);
	public Usuario login(String senha, String usuario);
}
