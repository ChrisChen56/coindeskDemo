package com.example.demo.mapper;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class Bpi {

	@JsonAlias("USD")
	ExchangeRateDetail usd;
	
	@JsonAlias("GBP")
	ExchangeRateDetail gbp;
	
	@JsonAlias("EUR")
	ExchangeRateDetail eur;
	
}
