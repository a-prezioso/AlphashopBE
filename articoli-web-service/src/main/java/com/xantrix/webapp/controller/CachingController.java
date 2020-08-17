package com.xantrix.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CachingController 
{
	 @Autowired
	 CacheManager cacheManager;
	 
	 @GetMapping("clearAllCaches")
	 public void clearAllCaches() 
	 {
		 cacheManager.getCache("articoli").clear();
		 cacheManager.getCache("articolo").clear();
	 }
}
