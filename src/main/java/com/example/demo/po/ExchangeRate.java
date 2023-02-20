package com.example.demo.po;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//幣別、匯率table	
@Data
@Entity
@Table(name="EXCHANGERATE")
public class ExchangeRate {
	
	@Id
	@Column(name = "CODE")
	private String code;
	@Column(name = "RATE")
	private Double rate;
	@Column(name = "CREATETIME")
	private Date createTime;
	@Column(name = "UPDATETIME")
	private Date updateTime;

}
