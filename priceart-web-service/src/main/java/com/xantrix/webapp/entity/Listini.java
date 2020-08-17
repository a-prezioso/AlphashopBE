package com.xantrix.webapp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "listini")
@Data
public class Listini implements Serializable
{ 
	private static final long serialVersionUID = 1891268953233014092L;
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Size(min = 10, max = 30, message = "{Size.Listini.descrizione.Validation}")
	@Basic
	private String descrizione;
	
	@Basic
	private String obsoleto;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "listino")
	@JsonManagedReference
	private Set<DettListini> dettListini = new HashSet<>();
	
	public Listini() {}
	
	public Listini(String Id, String Descrizione, String Obsoleto)
	{
		this.id = Id;
		this.descrizione = Descrizione;
		this.obsoleto = Obsoleto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getObsoleto() {
		return obsoleto;
	}

	public void setObsoleto(String obsoleto) {
		this.obsoleto = obsoleto;
	}

	public Set<DettListini> getDettListini() {
		return dettListini;
	}

	public void setDettListini(Set<DettListini> dettListini) {
		this.dettListini = dettListini;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
