package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
 

import lombok.Data;

@Entity
@Table(name = "CARDS")
@Data
public class Cards implements Serializable
{
	private static final long serialVersionUID = -2044872204293485177L;

	@Id
	@Column(name = "codfidelity")
	private String codfidelity;
	
	@Basic
	private int bollini;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date ultimaspesa;
	
	@Basic
	private String obsoleto;
	
	@OneToOne
	@PrimaryKeyJoinColumn 
	@JsonBackReference
	private Clienti cliente;

	public String getCodfidelity() {
		return codfidelity;
	}

	public void setCodfidelity(String codfidelity) {
		this.codfidelity = codfidelity;
	}

	public int getBollini() {
		return bollini;
	}

	public void setBollini(int bollini) {
		this.bollini = bollini;
	}

	public Date getUltimaspesa() {
		return ultimaspesa;
	}

	public void setUltimaspesa(Date ultimaspesa) {
		this.ultimaspesa = ultimaspesa;
	}

	public String getObsoleto() {
		return obsoleto;
	}

	public void setObsoleto(String obsoleto) {
		this.obsoleto = obsoleto;
	}

	public Clienti getCliente() {
		return cliente;
	}

	public void setCliente(Clienti cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
