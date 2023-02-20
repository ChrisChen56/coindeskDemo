package com.example.demo.po;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//幣別對應中文table	
@Data
@Entity
@Table(name="TRMVAL")
public class Trmval {
	@Id
	@Column(name = "CODE")
	private String code;
	@Column(name = "MEAN")
	private String mean;
}
