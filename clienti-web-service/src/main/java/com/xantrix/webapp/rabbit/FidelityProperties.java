package com.xantrix.webapp.rabbit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "fidelity")
@Data
public class FidelityProperties 
{
    private int valminimo;
    private int valbollino;
    private int bonusmobile;
	public int getValminimo() {
		return valminimo;
	}
	public void setValminimo(int valminimo) {
		this.valminimo = valminimo;
	}
	public int getValbollino() {
		return valbollino;
	}
	public void setValbollino(int valbollino) {
		this.valbollino = valbollino;
	}
	public int getBonusmobile() {
		return bonusmobile;
	}
	public void setBonusmobile(int bonusmobile) {
		this.bonusmobile = bonusmobile;
	}
    
    
}
