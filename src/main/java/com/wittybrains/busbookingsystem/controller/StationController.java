
package com.wittybrains.busbookingsystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wittybrains.busbookingsystem.dto.StationDTO;
import com.wittybrains.busbookingsystem.exception.StationNotFoundException;
import com.wittybrains.busbookingsystem.repository.StationRepository;
import com.wittybrains.busbookingsystem.service.StationService;

@RestController
@RequestMapping("/stations")
public class StationController {

	@Autowired
	private StationRepository stationRepository;
	private final Logger logger = LoggerFactory.getLogger(StationController.class);

	@PostMapping
	public ResponseEntity<String> createStation(@RequestBody StationDTO stationDTO) {
		try {
			// Create a new Station object
			StationService stationService = new StationService(stationRepository);
			stationService.createStation(stationDTO);

			logger.info("Successfully created station with code: {}", stationDTO.getStationCode());
			return new ResponseEntity<>("Successfully created station", HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			logger.error("Error creating station: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating station: " + e.getMessage());
		} catch (StationNotFoundException e) {
			logger.error("Error creating station: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error creating station: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Error creating station: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error creating station: " + e.getMessage());
		}
	}
}
