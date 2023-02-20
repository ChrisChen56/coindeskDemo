package com.example.demo.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CurrencyDetail {
	private String currencyCode;
	private String currencyMeaning;
	private Double rate;
}
