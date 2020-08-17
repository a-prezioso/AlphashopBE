package com.xantrix.webapp.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

 
import com.xantrix.webapp.dtos.OrderDTO;
import com.xantrix.webapp.service.ClientiService;

@Component
public class OrderListener 
{
	private static final Logger logger = LoggerFactory.getLogger(OrderListener.class);
	
	@Autowired
	ClientiService clientiService;
	
	@Autowired
	FidelityProperties fidelityProperties;
	
	@RabbitListener(queues = "alphashop.order.queue") 
	public void receiveOrder(OrderDTO ordine, @Header("X_ORDER_SOURCE") String Source) 
	{
		int bollini = 0;
		
		logger.info(String.format("Ricevuto Ordine %s di euro %s", ordine.getId(), ordine.getTotale()));
		
		if (ordine.getIdcliente().length() > 0 && ordine.getTotale() >= fidelityProperties.getValminimo())
		{
		    logger.info(String.format("***** Calcolo Bollini Cliente %s *****", ordine.getIdcliente()));
		   
		    bollini = (int) ordine.getTotale() / fidelityProperties.getValbollino();
		    
		    if (Source.equals("MOBILE"))
		    {	logger.info(String.format("Fonte %s. Eseguo Bonus Bollini!", Source));
		    	bollini *= fidelityProperties.getBonusmobile();
		    }
		    
		    clientiService.UpdMonteBollini(ordine.getIdcliente(), bollini);
		    
		    logger.info(String.format("Modificato monte bollini fidelity %s di %s bollini", ordine.getIdcliente(), bollini));
		}
		else
		{
			 logger.warn("Impossibile modificare il monte bollini ");
		}
	}	
}
