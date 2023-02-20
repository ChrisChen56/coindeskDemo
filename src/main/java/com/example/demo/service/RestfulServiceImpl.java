package com.example.demo.service;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CoindeskRateDto;
import com.example.demo.dto.CurrencyDetail;
import com.example.demo.dto.ExchangeRateDto;
import com.example.demo.mapper.CoindeskRate;
import com.example.demo.mapper.ExchangeRateDetail;
import com.example.demo.po.ExchangeRate;
import com.example.demo.repository.ExchangeRateRepository;
import com.example.demo.repository.TrmvalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestfulServiceImpl implements RestfulService {

	@Autowired
	ExchangeRateRepository exchangeRateRepo;

	@Autowired
	TrmvalRepository trmvalRepository;

	@Override
	public List<ExchangeRate> getAllExchangeRate() {
		return exchangeRateRepo.findAll();
	}

	@Override
	public ExchangeRate getExchangeRate(String code) {
		return exchangeRateRepo.findByCode(code);
	}

	@Override
	public void createExchangeRate(ExchangeRateDto exchangeRateDto) {
		ExchangeRate exchangeRate = new ExchangeRate();
		Calendar cal = Calendar.getInstance();
		exchangeRate.setCode(exchangeRateDto.getCode());
		exchangeRate.setRate(exchangeRateDto.getRate());
		exchangeRate.setCreateTime(cal.getTime());
		exchangeRate.setUpdateTime(cal.getTime());
		exchangeRateRepo.save(exchangeRate);
	}

	@Override
	public ExchangeRate updateExchangeRate(String code, ExchangeRateDto exchangeRateDto) {
		ExchangeRate exchangeRate = exchangeRateRepo.findByCode(code);
		if (exchangeRate != null) {
			Calendar cal = Calendar.getInstance();
			exchangeRate.setCode(code);
			exchangeRate.setRate(exchangeRateDto.getRate());
			exchangeRate.setUpdateTime(cal.getTime());
			exchangeRateRepo.save(exchangeRate);
		}
		return exchangeRate;
	}

	@Override
	public void deleteExchangeRate(String code) {
		exchangeRateRepo.deleteByCode(code);
	}

	@Override
	public CoindeskRate getCoindeskRate() {
		ObjectMapper objectMapper = new ObjectMapper();
		CoindeskRate coindeskRate = new CoindeskRate();
		try {
			URL jsonUrl = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");
			//使用ObjectMapper把JSON轉換為指定類別
			coindeskRate = objectMapper.readValue(jsonUrl, CoindeskRate.class);
			System.out.println(coindeskRate);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return coindeskRate;
	}

	@Override
	public CoindeskRateDto getTransferedCoindeskRate() {
		CoindeskRateDto coindeskRateDto = new CoindeskRateDto();
		CoindeskRate coindeskRate = getCoindeskRate();
		
		//更新時間
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String formattedUpdateTime = simpleDateFormat.format(coindeskRate.getTime().getUpdatedISO());
		coindeskRateDto.setUpdateTime(formattedUpdateTime);

		//幣別相關資訊
		List<ExchangeRateDetail> exchangeRateDetailList = new ArrayList<ExchangeRateDetail>();
		exchangeRateDetailList.add(coindeskRate.getBpi().getUsd());
		exchangeRateDetailList.add(coindeskRate.getBpi().getGbp());
		exchangeRateDetailList.add(coindeskRate.getBpi().getEur());
		List<CurrencyDetail> currencyDetailList = exchangeRateDetailList.stream()
				.map(rateDetail -> new CurrencyDetail(rateDetail.getCode(),
						trmvalRepository.findByCode(rateDetail.getCode()).getMean(), rateDetail.getRate_float()))
				.toList();
		coindeskRateDto.setCurrencyDetail(currencyDetailList);

		return coindeskRateDto;
	}

}
