package com.xantrix.webapp.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name ="PromoWebService") 
public interface PromoClient 
{
	@GetMapping(value = "api/promo/prezzo/{codart}")
    public Double getPromoArt(@RequestHeader("Authorization") String AuthHeader, @PathVariable("codart") String CodArt);
	
	@GetMapping(value = "api/promo/prezzo/fidelity/{codart}")
    public Double getPromoArtFid(@RequestHeader("Authorization") String AuthHeader, @PathVariable("codart") String CodArt);
	
	@GetMapping(value = "api/promo/prezzo/{codart}/{codfid}")
    public Double getPromoOnlyFid(@RequestHeader("Authorization") String AuthHeader, @PathVariable("codart") String CodArt, @PathVariable("codfid") String CodFid);
	
}
