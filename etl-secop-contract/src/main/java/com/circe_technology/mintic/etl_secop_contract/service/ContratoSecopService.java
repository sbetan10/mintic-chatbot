package com.circe_technology.mintic.etl_secop_contract.service;

import com.circe_technology.mintic.etl_secop_contract.config.SecopApiProperties;
import com.circe_technology.mintic.etl_secop_contract.dto.SecopContratoDto;
import com.circe_technology.mintic.etl_secop_contract.entity.ContratoSecop;
import com.circe_technology.mintic.etl_secop_contract.repository.ContratoSecopRepository;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class ContratoSecopService {

    private final RestTemplate restTemplate;
    private final SecopApiProperties properties;
    private final ContratoSecopRepository repository;

    /**
     * Executes the full fetching and persistence process, iterating through all pages.
     */
    public void fetchAndPersistAllContracts() {
        log.info("Starting contract data synchronization from SECOP API...");

        //int pageNumber = 1525; // Start from the first page
        int pageNumber = 1;
        int pageSize = properties.getPageSize();
        long totalRecordsSaved = 0;

        while (true) {
            List<ContratoSecop> contracts = fetchPage(pageNumber, pageSize);

            if (contracts.isEmpty()) {
                log.info("Received empty response. Synchronization finished successfully.");
                break; // Exit the loop if no records are returned
            }

            // Map DTOs to Entities and save in bulk
            List<ContratoSecop> savedEntities = repository.saveAll(contracts);

            totalRecordsSaved += savedEntities.size();
            log.info("Page {} processed. Saved {} records. Total records saved so far: {}",
                    pageNumber, savedEntities.size(), totalRecordsSaved);

            // If the number of records returned is less than the page size, it's the last page.
            if (contracts.size() < pageSize) {
                log.info(
                        "Last page reached (returned {} records, expected {}). Synchronization "
                                + "finished.",
                        contracts.size(), pageSize);
                break;
            }

            pageNumber++;
//            // Simple delay to avoid overwhelming the external API, especially during a large sync
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        }

        log.info("Synchronization complete. Total records persisted: {}", totalRecordsSaved);
    }

    /**
     * Fetches a single page of contract data from the API.
     *
     * @param pageNumber The page number to fetch.
     * @param pageSize   The number of records per page.
     * @return A list of ContratoSecop entities ready for persistence.
     */
    private List<ContratoSecop> fetchPage(int pageNumber, int pageSize) {
        log.debug("Fetching page {} with size {}", pageNumber, pageSize);

        // ID Conjunto de datos Secop II - Contratos electronicos: jbjy-vk9h
        // ID Conjunto de datos Secop II - Adiciones: cb9c-h8sn
        // ID Conjunto de datos Secop II - Contacto entidades y proveedores: 4ex9-j3n8

        String uriString = properties.getBaseUrl()
                + "jbjy-vk9h/query.json?app_token="
                + properties.getAppToken()
                + "&pageNumber="
                + pageNumber
                + "&pageSize="
                + pageSize
                + "&query=SELECT%20*%20WHERE%20%60nit_entidad%60%3D'899999118'";

        try {
            URI uri = new URI(uriString);
            // 2. Execute the synchronous GET request
            ResponseEntity<SecopContratoDto[]> response = restTemplate.getForEntity(
                    uri,
                    SecopContratoDto[].class
            );

            // 3. Process the response body if status is successful (Rest Template throws
            // exceptions otherwise)
            SecopContratoDto[] dtos = response.getBody();
            if (dtos == null) {
                return Collections.emptyList();
            }

            // 4. Map DTO array to List of Entities
            return Arrays.stream(dtos)
                    .map(SecopContratoDto::toEntity)
                    .toList();

        } catch (Exception e) {
            // Catches any exception, including connection issues, 4xx, 5xx errors, etc.
            log.info("Error fetching page {} with RestTemplate: {}", pageNumber, e.getMessage());
            return Collections.emptyList();
        }
    }
}
