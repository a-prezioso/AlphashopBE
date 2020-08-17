package com.xantrix.webapp.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.xantrix.webapp.entities.Barcode;
import com.xantrix.webapp.entities.FamAssort;
import com.xantrix.webapp.entities.Ingredienti;
import com.xantrix.webapp.entities.Iva;

import lombok.Data;

@Data
public class ArticoliDTO 
{
	private String codArt;
	private String descrizione;	
	private String um;
	private String codStat;
	private Integer pzCart;
	private double pesoNetto;
	private String idStatoArt;
	private Date dataCreaz;
	private double prezzo;
	private double promo;
	
	private Set<Barcode> barcode = new HashSet<>();
	private Ingredienti ingredienti;
	private FamAssort famAssort;
	private Iva iva;
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
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public Set<Barcode> getBarcode() {
		return barcode;
	}
	public void setBarcode(Set<Barcode> barcode) {
		this.barcode = barcode;
	}
	public Ingredienti getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(Ingredienti ingredienti) {
		this.ingredienti = ingredienti;
	}
	public FamAssort getFamAssort() {
		return famAssort;
	}
	public void setFamAssort(FamAssort famAssort) {
		this.famAssort = famAssort;
	}
	public Iva getIva() {
		return iva;
	}
	public void setIva(Iva iva) {
		this.iva = iva;
	}
	public double getPromo() {
		return promo;
	}
	public void setPromo(double promo) {
		this.promo = promo;
	}
	
	
	
	
}
