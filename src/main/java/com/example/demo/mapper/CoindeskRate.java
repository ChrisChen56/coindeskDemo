package com.example.demo.mapper;

import lombok.Data;

@Data
public class CoindeskRate {
	private Time time;
	private String disclaimer;
	private String chartName;
	private Bpi bpi;
}
