package com.xantrix.webapp.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xantrix.webapp.entities.DettPromo;
import com.xantrix.webapp.repository.PrezziPromoRepository;

@Service
@Transactional
public class PrezzoServiceImpl implements PrezzoService
{
	private static final Logger logger = LoggerFactory.getLogger(PrezzoService.class);

	@Autowired
	PrezziPromoRepository prezziPromoRep;
	
	//HISTRIX CONSTANT SETTINGS
	// altre opzioni https://github.com/Netflix/Hystrix/wiki/Configuration
	public static final String FailureTimOutInMs = "6000"; //Timeout in ms prima di failure e fallback logic (def 1000)
	public static final String RequestVolumeThreshold = "10"; //Numero Minimo di richieste prima di aprire il circuito (Def 20)
	public static final String ErrorThresholdPercentage = "30"; //Percentuale minima di fallimenti prima di apertura del circuito (Def 50)
	public static final String SleepTimeInMs = "5000"; //Tempo in ms prima di ripetere tentativo di chiusura del circuito (def 5000)
	public static final String TimeMetricInMs = "5000"; //Tempo base in ms delle metriche statistiche 


	@Override
	@HystrixCommand(fallbackMethod = "SelPromoByCodArtFallBack",
	commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = FailureTimOutInMs),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = RequestVolumeThreshold),
		    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = ErrorThresholdPercentage),
		    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = SleepTimeInMs),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = TimeMetricInMs)
		})
	public Double SelPromoByCodArt(String CodArt) 
	{
		double retVal = 0;

		DettPromo promo =  prezziPromoRep.SelByCodArt(CodArt);
		
		if (promo != null)
		{
			if (promo.getTipoPromo().getIdTipoPromo() == 1)
			{
				try
				{
					retVal = Double.parseDouble(promo.getOggetto().replace(",", "."));
				}
				catch(NumberFormatException ex)
				{
					logger.warn(ex.getMessage());
				}
			}
			else  //TODO Gestire gli altri tipi di promozione
			{
				retVal = 0;
			}
		}
		else
		{
			logger.warn("Promo Articolo Assente!!");
		}

		return retVal;
	}
	
	public Double SelPromoByCodArtFallBack(String CodArt)
	{
		logger.warn("****** SelPromoByCodArtFallBack in esecuzione *******");
		
		return 0.00;
	}
	
	@Override
	public Double SelPromoByCodArtAndFid(String CodArt) 
	{
		double retVal = 0;

		DettPromo promo =  prezziPromoRep.SelByCodArtAndFid(CodArt);
		
		if (promo != null)
		{
			if (promo.getTipoPromo().getIdTipoPromo() == 1)
			{
				try
				{
					retVal = Double.parseDouble(promo.getOggetto().replace(",", "."));
				}
				catch(NumberFormatException ex)
				{
					logger.warn(ex.getMessage());
				}
			}
			else  //TODO Gestire gli altri tipi di promozione
			{
				retVal = 0;
			}
		}
		else
		{
			logger.warn("Promo Articolo Fidelity Assente!!");
		}

		return retVal;
	}
	
	@Override
	public Double SelByCodArtAndCodFid(String CodArt, String CodFid) 
	{
		double retVal = 0;

		DettPromo promo =  prezziPromoRep.SelByCodArtAndCodFid(CodArt, CodFid);
		
		if (promo != null)
		{
			if (promo.getTipoPromo().getIdTipoPromo() == 1)
			{
				try
				{
					retVal = Double.parseDouble(promo.getOggetto().replace(",", "."));
				}
				catch(NumberFormatException ex)
				{
					logger.warn(ex.getMessage());
				}
			}
		}
		else
		{
			logger.warn(String.format("Promo Riservata Fidelity %s Assente!!", CodFid) );
		}

		return retVal;
	}
	
	@Override
	public void UpdOggettoPromo(String Oggetto, Long Id) 
	{
		// TODO Auto-generated method stub
	}
	
}
