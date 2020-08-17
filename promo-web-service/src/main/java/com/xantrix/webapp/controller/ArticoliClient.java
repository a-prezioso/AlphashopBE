package com.xantrix.webapp.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.xantrix.webapp.entities.Articoli;

@FeignClient(name ="ProductsWebService") 
public interface ArticoliClient 
{
	@GetMapping(value = "api/articoli/cerca/codice/{codart}")
    public Articoli getArticolo(@PathVariable("codart") String CodArt);
	
}
