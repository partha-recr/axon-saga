package com.progressivecoder.ecommerce.events;

public class CanceledOrderRequest {

	public final String orderId;

	public CanceledOrderRequest(String orderId) {
		super();
		this.orderId = orderId;
	}
}
