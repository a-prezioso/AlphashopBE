package com.xantrix.webapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xantrix.webapp.entities.Articoli;
import com.xantrix.webapp.repository.ArticoliRepository;

@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames={"articoli"})
public class ArticoliServiceImpl implements ArticoliService
{
	@Autowired
	ArticoliRepository articoliRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ArticoliServiceImpl.class);
	
	//HISTRIX CONSTANT SETTINGS
	// altre opzioni https://github.com/Netflix/Hystrix/wiki/Configuration
	public static final String FailureTimOutInMs = "6000"; //Timeout in ms prima di failure e fallback logic (def 1000)
	public static final String RequestVolumeThreshold = "10"; //Numero Minimo di richieste prima di aprire il circuito (Def 20)
	public static final String ErrorThresholdPercentage = "30"; //Percentuale minima di fallimenti prima di apertura del circuito (Def 50)
	public static final String SleepTimeInMs = "5000"; //Tempo in ms prima di ripetere tentativo di chiusura del circuito (def 5000)
	public static final String TimeMetricInMs = "5000"; //Tempo base in ms delle metriche statistiche 

	@Override
	@Cacheable
	@HystrixCommand(fallbackMethod = "SelByDescrizioneFallBack",
	commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = FailureTimOutInMs),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = RequestVolumeThreshold),
		    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = ErrorThresholdPercentage),
		    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = SleepTimeInMs),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = TimeMetricInMs)
		})
	public List<Articoli> SelByDescrizione(String descrizione) 
	{
		return articoliRepository.findByDescrizioneLike(descrizione.toUpperCase());
	}
	
	public List<Articoli> SelByDescrizioneFallBack(String descrizione) 
	{
		logger.warn("****** SelByDescrizioneFallBack Attivato *******");
		
		//TODO prevedere la lettura in fonte dati alternativa
		
		return null;
	}

	@Override
	@Cacheable(value = "articolo",key = "#codArt",sync = true)
	@HystrixCommand(fallbackMethod = "SelByCodArtFallBack",
	commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = FailureTimOutInMs),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = RequestVolumeThreshold),
		    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = ErrorThresholdPercentage),
		    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = SleepTimeInMs),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = TimeMetricInMs)
		})
	public Articoli SelByCodArt(String codArt) 
	{
		 
		return articoliRepository.findByCodArt(codArt);
	}
	
	public Articoli SelByCodArtFallBack(String codArt) 
	{
		logger.warn("****** SelByCodArtFallBack Attivato *******");
		
		//TODO prevedere la lettura in fonte dati alternativa
		
		return null;
	}

	@Override
	@Cacheable
	@HystrixCommand(fallbackMethod = "SelByBarcodeFallBack",
	commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = FailureTimOutInMs),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = RequestVolumeThreshold),
		    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = ErrorThresholdPercentage),
		    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = SleepTimeInMs),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = TimeMetricInMs)
		})
	public Articoli SelByBarcode(String barcode) 
	{
		
		return articoliRepository.SelByEan(barcode);
	}
	
	public Articoli SelByBarcodeFallBack(String barcode) 
	{
		logger.warn("****** SelByBarcodeFallBack Attivato *******");
		
		//TODO prevedere la lettura in fonte dati alternativa
		
		return null;
	}

	@Override
	@Transactional
	@Caching(evict = { 
			@CacheEvict(cacheNames="articoli", allEntries = true),
			@CacheEvict(cacheNames="articolo",key = "#articolo.codArt")
			})
	public void DelArticolo(Articoli articolo) 
	{
		articoliRepository.delete(articolo);
	}

	@Override
	@Transactional
	@Caching(evict = { 
			@CacheEvict(cacheNames="articoli", allEntries = true),
			@CacheEvict(cacheNames="articolo",key = "#articolo.codArt")
			})
	public void InsArticolo(Articoli articolo) 
	{
		articoliRepository.save(articolo);
	}

}