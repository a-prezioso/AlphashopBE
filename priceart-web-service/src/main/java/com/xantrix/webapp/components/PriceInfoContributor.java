package com.xantrix.webapp.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.xantrix.webapp.appconf.AppConfig;
import com.xantrix.webapp.repository.ListinoRepository;

@Component
public class PriceInfoContributor implements InfoContributor 
{
    @Autowired
    private AppConfig configuration;
    
    private ListinoRepository prezzi;

    public PriceInfoContributor(ListinoRepository prezzi)
    {
        this.prezzi = prezzi;
    }

    @Override
    public void contribute(Builder builder) 
    {
        String Listino = configuration.getListino();
        
        
        long QtaPrezzi = prezzi.GetQtaDettList(Listino);

        Map<String, Object> priceMap = new HashMap<String, Object>();
        priceMap.put("listino", Listino);
        priceMap.put("qta", QtaPrezzi);
        builder.withDetail("prezzi-info", priceMap);
	}

}