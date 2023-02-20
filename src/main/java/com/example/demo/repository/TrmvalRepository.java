package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.po.Trmval;

@Repository
public interface TrmvalRepository extends JpaRepository<Trmval, Long>{

	Trmval findByCode(String code);
}
