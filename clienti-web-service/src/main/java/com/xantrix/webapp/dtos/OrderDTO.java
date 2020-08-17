package com.xantrix.webapp.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class OrderDTO implements Serializable
{
	private static final long serialVersionUID = 1407680853764474369L;
	
	public String id;
	public Date data;
	public double totale;
	public String idcliente;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getTotale() {
		return totale;
	}
	public void setTotale(double totale) {
		this.totale = totale;
	}
	public String getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
		
}
