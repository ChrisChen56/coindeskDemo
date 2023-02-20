package com.example.demo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CoindeskRateDto;
import com.example.demo.dto.ExchangeRateDto;
import com.example.demo.mapper.CoindeskRate;
import com.example.demo.po.ExchangeRate;

@Transactional
public interface RestfulService {
	
	List<ExchangeRate> getAllExchangeRate();

	ExchangeRate getExchangeRate(String code);
	
	void createExchangeRate(ExchangeRateDto exchangeRateDto);
	
	ExchangeRate updateExchangeRate(String code, ExchangeRateDto exchangeRateDto);
	
	void deleteExchangeRate(String code);
	
	CoindeskRate getCoindeskRate();
	
	CoindeskRateDto getTransferedCoindeskRate();
}
