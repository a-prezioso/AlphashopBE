package com.xantrix.webapp.controller;

 
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xantrix.webapp.dtos.ClientiDTO;
import com.xantrix.webapp.entities.Clienti;
import com.xantrix.webapp.exception.NotFoundException;
import com.xantrix.webapp.service.ClientiService;

@RestController
@RequestMapping("/api/clienti")
public class ClientiController 
{
	private static final Logger logger = LoggerFactory.getLogger(ClientiController.class);
	
	@Autowired
	ClientiService clientiService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// ------------------- Ricerca Per Codice ------------------------------------
	@RequestMapping(value = "/cerca/codice/{idcliente}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ClientiDTO> listCliByCode(@PathVariable("idcliente") String IdCliente,
			HttpServletRequest httpRequest)
		throws NotFoundException	 
	{
		logger.info("****** Otteniamo il cliente con codice " + IdCliente + " *******");
		
		//String AuthHeader = httpRequest.getHeader("Authorization");

		Clienti cliente = clientiService.SelByIdCliente(IdCliente);
		
		ClientiDTO clienteDTO;
		
		if (cliente == null)
		{
			String ErrMsg = String.format("Il cliente %s non è stato trovato!", IdCliente);
			
			logger.warn(ErrMsg);
			
			throw new NotFoundException(ErrMsg);
		}
		else
		{
			clienteDTO = modelMapper.map(cliente, ClientiDTO.class);
			
			clienteDTO.setNominativo(cliente.getNome() + " " + cliente.getCognome());
			clienteDTO.setBollini(cliente.getCards().getBollini());
			clienteDTO.setUltimaSpesa(cliente.getCards().getUltimaspesa());
		}
		
		 
		return new ResponseEntity<ClientiDTO>(clienteDTO, HttpStatus.OK);
	}
	
	@PutMapping(value = "/cards/modifica/{idcliente}/{bollini}")
	public ResponseEntity<?> UpdBollini(@PathVariable("idcliente") String IdCliente, @PathVariable("bollini") int Bollini)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		if (IdCliente.length() > 0 && Bollini != 0)
		{
		    logger.info(String.format("***** Modifica Monte Bollini Cliente %s *****", IdCliente));
		}
		else
		{
			 logger.warn("Impossibile modificare con il metodo POST ");
			 
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseNode = mapper.createObjectNode();
		
		clientiService.UpdMonteBollini(IdCliente, Bollini);
		
		responseNode.put("code", HttpStatus.OK.toString());
		responseNode.put("message", String.format("Monte bollini cliente %s modificato di (%s) Bollini",IdCliente,Bollini));
		
		return new ResponseEntity<>(responseNode, headers, HttpStatus.OK);
	}
	
}
