



package com.wittybrains.busbookingsystem.controller;

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
import com.wittybrains.busbookingsystem.dto.BookingDTO;
import com.wittybrains.busbookingsystem.exception.TravelScheduleNotFoundException;
import com.wittybrains.busbookingsystem.exception.UserNotFoundException;
import com.wittybrains.busbookingsystem.service.BookingService;

@JsonInclude(value = Include.NON_NULL)
@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

	private final BookingService bookingService;
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@PostMapping
	public ResponseEntity<Object> createBooking(@RequestBody BookingDTO bookingDTO) {
	    LOGGER.info("Received request to create a booking");

	    try {
	        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
	        LOGGER.info("Created booking");
	
	        return ResponseEntity.ok(createdBooking);
	    } catch (UserNotFoundException e) {
	        LOGGER.error("User not found for id: {}", bookingDTO.getUser().getId(), e);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for id: " + bookingDTO.getUser().getId());
	    } catch (TravelScheduleNotFoundException e) {
	        LOGGER.error("Travel schedule not found for id: {}", bookingDTO.getSchedule().getId(), e);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Travel schedule not found for id: " + bookingDTO.getSchedule().getId());
	    }
	}

}

