package com.gestaoevento.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Evento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="evento_id_seq")
    @SequenceGenerator(name="evento_id_seq", sequenceName="evento_id_seq", allocationSize=1)
	private Long id;

	private String descricao;
	
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name="id_local")
	private Localidade localidade;
	
	
	public Evento(Long id, String descricao, Date data, String status, Pessoa pessoa,
			Localidade localidade) {
		this.id = id;
		this.descricao = descricao;
		this.data = data;
		this.status = status;
		this.pessoa = pessoa;
		this.localidade = localidade;
	}
	
	public Evento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "equipamento_evento", 
	    joinColumns = @JoinColumn(name = "id_evento", referencedColumnName = "id"), 
	    inverseJoinColumns = @JoinColumn(name = "id_equipamento", referencedColumnName = "id"))
	private Set<Equipamento> equipamentos = new HashSet<Equipamento>();
	
	public Set<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(Set<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
	
	
	

	public Evento(Long id, String descricao, Date data, String status, Pessoa pessoa, Localidade localidade,
			Set<Equipamento> equipamentos) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.data = data;
		this.status = status;
		this.pessoa = pessoa;
		this.localidade = localidade;
		this.equipamentos = equipamentos;
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
		Evento other = (Evento) obj;
		if(id == null){
			if(other.id != null)
				return false;
		}else if (!id.equals(other.id)) 
			return false;
		return true;
	}
}
