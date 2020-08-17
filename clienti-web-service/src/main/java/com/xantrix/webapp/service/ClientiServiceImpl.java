package com.xantrix.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xantrix.webapp.entities.Clienti;
import com.xantrix.webapp.repository.ClientiRepository;

@Service
@Transactional(readOnly = true)
public class ClientiServiceImpl implements ClientiService
{

	@Autowired
	ClientiRepository clientiRepository;
	
	@Override
	public Clienti SelByIdCliente(String IdCliente) 
	{
		return clientiRepository.findByCodfidelity(IdCliente);
	}

	@Override
	@Transactional
	public void UpdMonteBollini(String CodFid, int Bollini) 
	{
		clientiRepository.UpdMonteBollini(CodFid, Bollini);
	}

}
