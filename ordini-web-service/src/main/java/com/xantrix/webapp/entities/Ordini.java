package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "ORDINI")
@Data
public class Ordini implements Serializable
{
	private static final long serialVersionUID = 3127983680192346940L;
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "DATA")
	@Temporal(TemporalType.DATE)
	private Date Data;
	
	@Basic
	private int idpdv;
	
	@Basic(optional = false)
	private String codfid;
	
	@Basic(optional = false)
	private int stato;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,  mappedBy = "ordine",  orphanRemoval = true)
	@OrderBy("id desc") 
	@JsonManagedReference
	private Set<DettOrdini> dettOrdine = new HashSet<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getData() {
		return Data;
	}

	public void setData(Date data) {
		Data = data;
	}

	public int getIdpdv() {
		return idpdv;
	}

	public void setIdpdv(int idpdv) {
		this.idpdv = idpdv;
	}

	public String getCodfid() {
		return codfid;
	}

	public void setCodfid(String codfid) {
		this.codfid = codfid;
	}

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}

	public Set<DettOrdini> getDettOrdine() {
		return dettOrdine;
	}

	public void setDettOrdine(Set<DettOrdini> dettOrdine) {
		this.dettOrdine = dettOrdine;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
