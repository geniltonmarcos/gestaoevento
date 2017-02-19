package com.gestaoevento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Localidade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="localidade_id_seq")
    @SequenceGenerator(name="localidade_id_seq", sequenceName="localidade_id_seq", allocationSize=1)
	private Long id;
	
	@Column(name="localidade")
	private String local;
		
	
	@Column(name="capacidade")
	private Integer capacidade; 
	
	private Boolean ativo;
	
	public Localidade(Long id, String local, Integer capacidade, Boolean ativo) {
		this.id = id;
		this.local = local;
		this.capacidade = capacidade;
		this.ativo = ativo;
	}

	public Localidade() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
		Localidade other = (Localidade) obj;
		if(id == null){
			if(other.id != null)
				return false;
		}else if (!id.equals(other.id)) 
			return false;
		return true;
	}
}
