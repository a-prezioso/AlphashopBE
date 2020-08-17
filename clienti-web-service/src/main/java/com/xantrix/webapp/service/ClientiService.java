package com.xantrix.webapp.service;

import com.xantrix.webapp.entities.Clienti;
 
public interface ClientiService 
{
	public Clienti SelByIdCliente(String IdCliente);
	
	public void UpdMonteBollini(String CodFid, int Bollini);
	
	
}
