package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Articoli  implements Serializable
{
	private static final long serialVersionUID = 291353626011036772L;
	
	
	private String codArt;
	private String descrizione;	
	private String um;
	private String codStat;
	private Integer pzCart;
	private double pesoNetto;
	private String idStatoArt;
	private Date dataCreaz;
	private Double prezzo;
	public String getCodArt() {
		return codArt;
	}
	public void setCodArt(String codArt) {
		this.codArt = codArt;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getUm() {
		return um;
	}
	public void setUm(String um) {
		this.um = um;
	}
	public String getCodStat() {
		return codStat;
	}
	public void setCodStat(String codStat) {
		this.codStat = codStat;
	}
	public Integer getPzCart() {
		return pzCart;
	}
	public void setPzCart(Integer pzCart) {
		this.pzCart = pzCart;
	}
	public double getPesoNetto() {
		return pesoNetto;
	}
	public void setPesoNetto(double pesoNetto) {
		this.pesoNetto = pesoNetto;
	}
	public String getIdStatoArt() {
		return idStatoArt;
	}
	public void setIdStatoArt(String idStatoArt) {
		this.idStatoArt = idStatoArt;
	}
	public Date getDataCreaz() {
		return dataCreaz;
	}
	public void setDataCreaz(Date dataCreaz) {
		this.dataCreaz = dataCreaz;
	}
	public Double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
