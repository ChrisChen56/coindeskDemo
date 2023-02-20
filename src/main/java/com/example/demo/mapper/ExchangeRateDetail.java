package com.example.demo.mapper;

import lombok.Data;

@Data
public class ExchangeRateDetail {
	private String code;
	private String symbol;
	private String rate;
	private String description;
	private Double rate_float;
}
