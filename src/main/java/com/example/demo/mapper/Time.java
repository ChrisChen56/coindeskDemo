package com.example.demo.mapper;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Time {

	private String updated;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss+ss:ss")
	private Date updatedISO;

	private String updateduk;

}
