package com.circe_technology.mintic.etl_secop_contract.repository;

import com.circe_technology.mintic.etl_secop_contract.entity.ContratoSecop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoSecopRepository extends JpaRepository<ContratoSecop, String> {
}
