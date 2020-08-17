package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "CLIENTI")
@Data
public class Clienti implements Serializable
{
	private static final long serialVersionUID = -4995740052474979576L;

	@Id
	@Column(name = "codfidelity")
	private String codfidelity;
	
	@Basic
	private String nome;
	
	@Basic
	private String cognome;
	
	@Basic
	private String indirizzo;
	
	@Basic
	private String comune;
	
	@Basic
	private String cap;
	
	@Basic
	private String telefono;
	
	@Basic
	private String mail;
	
	@Basic
	private String stato;
	
	@Temporal(TemporalType.DATE)
	@Basic
	private Date datacreaz;
	
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Cards cards;

	public String getCodfidelity() {
		return codfidelity;
	}

	public void setCodfidelity(String codfidelity) {
		this.codfidelity = codfidelity;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Date getDatacreaz() {
		return datacreaz;
	}

	public void setDatacreaz(Date datacreaz) {
		this.datacreaz = datacreaz;
	}

	public Cards getCards() {
		return cards;
	}

	public void setCards(Cards cards) {
		this.cards = cards;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
