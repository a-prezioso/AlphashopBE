package com.xantrix.webapp.appconf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("application")
@Data
public class AppConfig
{
	private String listino;
	private Double sconto = 0.00;
	public String getListino() {
		return listino;
	}
	public void setListino(String listino) {
		this.listino = listino;
	}
	public Double getSconto() {
		return sconto;
	}
	public void setSconto(Double sconto) {
		this.sconto = sconto;
	}
	
	
}
