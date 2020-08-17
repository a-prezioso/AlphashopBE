package com.xantrix.webapp.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name ="PriceArtWebService")
@RibbonClient(name="PriceArtWebService")
public interface PriceClient 
{
	@GetMapping(value = "/api/prezzi/{codart}")
    public Double getDefPriceArt(@RequestHeader("Authorization") String AuthHeader, @PathVariable("codart") String CodArt);
	
	@GetMapping(value = "/api/prezzi/{codart}/{idlist}")
    public Double getPriceArt(@RequestHeader("Authorization") String AuthHeader, @PathVariable("codart") String CodArt, 
    		@PathVariable("idlist") String IdList);
	
}
