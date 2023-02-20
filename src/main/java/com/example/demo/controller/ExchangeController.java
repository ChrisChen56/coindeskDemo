package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CoindeskRateDto;
import com.example.demo.dto.ExchangeRateDto;
import com.example.demo.mapper.CoindeskRate;
import com.example.demo.po.ExchangeRate;
import com.example.demo.service.RestfulServiceImpl;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExchangeController {

	@Autowired
	RestfulServiceImpl restfulServiceImpl;

	//查詢全部
	@GetMapping("/exchangeRate")
	public ResponseEntity<List<ExchangeRate>> getAllExchangeRate() {
		return ResponseEntity.ok().body(restfulServiceImpl.getAllExchangeRate());
	}

	//查詢
	@GetMapping("/exchangeRate/{code}")
	public ResponseEntity<ExchangeRate> getExchangeRate(@PathVariable("code") String code) {
		return ResponseEntity.ok().body(restfulServiceImpl.getExchangeRate(code));
	}

	//新增
	@PostMapping("/exchangeRate")
	public void createExchangeRate(@RequestBody ExchangeRateDto exchangeRateDto) {
		restfulServiceImpl.createExchangeRate(exchangeRateDto);
	}

	//修改
	@PutMapping("/{code}")
	public ResponseEntity<ExchangeRate> updateExchangeRate(@PathVariable("code") String code,
			@RequestBody ExchangeRateDto exchangeRateDto) {
		ExchangeRate exchangeRate = restfulServiceImpl.updateExchangeRate(code, exchangeRateDto);
		if (exchangeRate != null) {
			return ResponseEntity.ok(exchangeRate); // ResponseEntity.ok().body(exchangeRate);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	//刪除
	@DeleteMapping("/{code}")
	public void deleteExchangeRate(@PathVariable("code") String code) {
		restfulServiceImpl.deleteExchangeRate(code);
	}

	//呼叫API，轉換為CoindeskRate類別再回傳
	@GetMapping("/coindeskRateAPI")
	public ResponseEntity<CoindeskRate> CoindeskRateAPI() {
		return ResponseEntity.ok(restfulServiceImpl.getCoindeskRate());
	}

	//API資料轉換
	@GetMapping("/coindeskRateTransferedAPI")
	public ResponseEntity<CoindeskRateDto> CoindeskRateTransferedAPI() {
		return ResponseEntity.ok(restfulServiceImpl.getTransferedCoindeskRate());
	}
}
