package com.circe_technology.mintic.etl_secop_contract.controller;

import com.circe_technology.mintic.etl_secop_contract.service.ContratoSecopService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/contracts")
@RestController
public class ContratoSecopController {
    private final ContratoSecopService contratoSecopService;


    @GetMapping("/sync")
    public void syncContracts() {
        contratoSecopService.fetchAndPersistAllContracts();
    }
}
