package com.xantrix.webapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saluti")
public class SalutiRestContoller 
{
	
	@GetMapping 
	public String getGreetings()
	{
		return ("Saluti, sono il tuo primo web service REST");
	}
	
	@RequestMapping(value =  "/{nome}", method = RequestMethod.GET, produces = "application/json")
	public String getGreetings2(@PathVariable("nome") String Nome)
	{
		return (String.format("Saluti %s, sono il tuo primo web service REST ", Nome));
	}
	
	@RequestMapping(value =  "/test/{nome}", method = RequestMethod.GET, produces = "application/json")
	public String getGreetings3(@PathVariable("nome") String Nome)
	{
		return (String.format("Saluti %s, sono il nuovo end point! ", Nome));
	}
}
