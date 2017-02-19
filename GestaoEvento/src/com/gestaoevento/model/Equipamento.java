package com.gestaoevento.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Equipamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="equipamento_id_seq")
    @SequenceGenerator(name="equipamento_id_seq", sequenceName="equipamento_id_seq", allocationSize=1)
	private Long id;
	
	@Column(name="status")
	private Boolean status;
	
	
	@Column(name="nome")
	private String nome; 
		

	public Equipamento(Long id, Boolean status, String tipo) {
		this.id = id;
		this.status = status;
		this.nome = tipo;
	}
	
	

	public Equipamento(Long id, Boolean status, String nome, Set<Evento> eventos) {
		this.id = id;
		this.status = status;
		this.nome = nome;
		this.eventos = eventos;
	}



	public Equipamento() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return nome;
	}

	public void setTipo(String tipo) {
		this.nome = tipo;
	}
	
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "equipamentos")
	private Set<Evento> eventos = new HashSet<Evento>(0);
	
	public Set<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Equipamento other = (Equipamento) obj;
		if(id == null){
			if(other.id != null)
				return false;
		}else if (!id.equals(other.id)) 
			return false;
		return true;
	}
}
