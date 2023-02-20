package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class CoindeskRateDto {
	private String updateTime;
	private List<CurrencyDetail> currencyDetail;
}
