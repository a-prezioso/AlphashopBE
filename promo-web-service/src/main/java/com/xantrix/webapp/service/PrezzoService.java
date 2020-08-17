package com.xantrix.webapp.service;

public interface PrezzoService 
{
	Double SelPromoByCodArt(String CodArt);
	
	Double SelPromoByCodArtAndFid(String CodArt);
	
	Double SelByCodArtAndCodFid(String CodArt, String CodFid);
	
	void UpdOggettoPromo(String Oggetto, Long Id);
}
