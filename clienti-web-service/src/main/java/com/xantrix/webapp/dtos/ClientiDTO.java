package com.xantrix.webapp.dtos;

import java.util.Date;
import lombok.Data;

@Data
public class ClientiDTO 
{
	private String codfidelity;
	private String nominativo;
	private String indirizzo;
	private String comune;
	private String cap;
	private String telefono;
	private String mail;
	private String stato;
	
	private int bollini;
	private Date ultimaSpesa;
	public String getCodfidelity() {
		return codfidelity;
	}
	public void setCodfidelity(String codfidelity) {
		this.codfidelity = codfidelity;
	}
	public String getNominativo() {
		return nominativo;
	}
	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
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
	public int getBollini() {
		return bollini;
	}
	public void setBollini(int bollini) {
		this.bollini = bollini;
	}
	public Date getUltimaSpesa() {
		return ultimaSpesa;
	}
	public void setUltimaSpesa(Date ultimaSpesa) {
		this.ultimaSpesa = ultimaSpesa;
	}
	
	
	
}
