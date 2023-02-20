package com.example.demo;

public enum Currency {
	USD("USD"), GBP("GBP"), EUR("EUR");

	private String currency;
	
	Currency(String currency) {
		this.currency = currency;
	};
}
