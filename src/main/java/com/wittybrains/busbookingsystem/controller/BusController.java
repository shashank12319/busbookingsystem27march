package com.wittybrains.busbookingsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wittybrains.busbookingsystem.dto.BusDTO;
import com.wittybrains.busbookingsystem.service.BusService;

@RestController
@RequestMapping("/bus")
@JsonInclude(value = Include.NON_NULL)
public class BusController {
	private final Logger logger = LoggerFactory.getLogger(BusController.class);
	private final BusService busService;

	public BusController(BusService busService) {
		this.busService = busService;
	}

	@PostMapping("/createBuses")
	public ResponseEntity<String> createBuses(@RequestBody List<BusDTO> busList) {
		logger.info("Creating buses");
		try {
			List<BusDTO> createdBuses = busService.createBuses(busList);

			if (createdBuses.isEmpty()) {
				logger.error("Failed to create buses");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create buses");
			}

			logger.info("Successfully created buses");
			return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created buses");
		} catch (IllegalArgumentException e) {
			logger.error("Failed to create buses: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
