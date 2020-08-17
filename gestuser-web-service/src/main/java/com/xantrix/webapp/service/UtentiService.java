package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.model.Utenti;
 
public interface UtentiService
{
	public List<Utenti> SelTutti();

	public Utenti SelUser(String bravura);
	
	public void Save(Utenti utente);
	
	public void Delete(Utenti utente);
	
}
