package com.xantrix.webapp.service;

import com.xantrix.webapp.dtos.OrderDTO;

public interface OrderMessagingService 
{
	public void sendOrder(OrderDTO ordine); 
}
