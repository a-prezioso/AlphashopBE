package com.xantrix.webapp.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xantrix.webapp.entity.DettListini;
import com.xantrix.webapp.repository.PrezziRepository;

@Service
@Transactional
public class PrezziServiceImpl implements PrezziService
{
	@Autowired
	PrezziRepository prezziRepository;
	
	@Autowired
	CacheManager cacheManager;
	
	//HISTRIX CONSTANT SETTINGS
	// altre opzioni https://github.com/Netflix/Hystrix/wiki/Configuration
	public static final String FailureTimOutInMs = "6000"; //Timeout in ms prima di failure e fallback logic (def 1000)
	public static final String RequestVolumeThreshold = "10"; //Numero Minimo di richieste prima di aprire il circuito (Def 20)
	public static final String ErrorThresholdPercentage = "30"; //Percentuale minima di fallimenti prima di apertura del circuito (Def 50)
	public static final String SleepTimeInMs = "5000"; //Tempo in ms prima di ripetere tentativo di chiusura del circuito (def 5000)
	public static final String TimeMetricInMs = "5000"; //Tempo base in ms delle metriche statistiche 

	
	private static final Logger logger = LoggerFactory.getLogger(PrezziServiceImpl.class);
	
	@Override
	@Cacheable(value = "prezzo",key = "#CodArt.concat('-').concat(#Listino)", sync = true)
	@HystrixCommand(fallbackMethod = "SelPrezzoFallBack",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = FailureTimOutInMs),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = RequestVolumeThreshold),
				    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = ErrorThresholdPercentage),
				    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = SleepTimeInMs),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = TimeMetricInMs)
				})
	public DettListini SelPrezzo(String CodArt, String Listino)
	{
		return prezziRepository.SelByCodArtAndList(CodArt, Listino);
		
		/*
		int randomNum = ThreadLocalRandom.current().nextInt(2000, 8000);
		
		try 
		{
			logger.warn(String.format("Applicato rallenentamento di %s", randomNum));
			//TODO Da Eliminare dopo test hystrix
			Thread.sleep(randomNum);
			return prezziRepository.SelByCodArtAndList(CodArt, Listino);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			return null;
		}
		*/
		
		//return prezziRepository.SelByCodArtAndList(CodArt, Listino);
	}
	
	public DettListini SelPrezzoFallBack(String CodArt, String Listino)
	{
		logger.warn("****** SelPrezzoFallBack in esecuzione *******");
		
		DettListini dettListini;

		dettListini = prezziRepository.SelByCodArtAndList(CodArt, Listino);
		
		if (dettListini != null)
		{
			logger.warn("Ottenuto Listino dalla Fonte Dati Alternativa");
		}
		else
		{
			logger.warn("Fonte Dati Alternativa non disponibile! Impossibile Ottenere il listino");

			dettListini = new DettListini();
			dettListini.setCodArt(CodArt);
			dettListini.setPrezzo(0.00);
		}
	
		//this.evictSingleCacheValue("prezzo", CodArt.concat("-").concat(Listino));
		
		return dettListini;
	}
	
	public void evictSingleCacheValue(String cacheName, String cacheKey) 
	{
		logger.warn(String.format("Cache %s con chiave %s eliminata", cacheName, cacheKey));
		
	    cacheManager.getCache(cacheName).evict(cacheKey);
	}
	
	
	@Caching(evict = { 
			@CacheEvict(cacheNames="prezzo",key = "#CodArt.concat('-').concat(#IdList)")
			})
	private void delCacheArt(String CodArt, String IdList)
	{
		logger.warn(String.format("Cache dell'articolo %s del listino %s eliminata", CodArt, IdList));
	}
	
	
	@Override
	@Caching(evict = { 
			@CacheEvict(cacheNames="prezzo",key = "#CodArt.concat('-').concat(#IdList)")
			})
	public void DelPrezzo(String CodArt, String IdList) 
	{
		prezziRepository.DelRowDettList(CodArt, IdList);
	}

}
