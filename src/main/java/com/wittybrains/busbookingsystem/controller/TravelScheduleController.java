package com.wittybrains.busbookingsystem.controller;



import java.text.ParseException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wittybrains.busbookingsystem.dto.TravelScheduleDTO;
import com.wittybrains.busbookingsystem.model.Bus;
import com.wittybrains.busbookingsystem.model.TravelSchedule;
import com.wittybrains.busbookingsystem.repository.BusRepository;
import com.wittybrains.busbookingsystem.repository.TravelScheduleRepository;
import com.wittybrains.busbookingsystem.service.TravelScheduleService;

@RestController
@RequestMapping("/schedules")
@JsonInclude(value = Include.NON_NULL)
public class TravelScheduleController {
	private static final Logger logger = LoggerFactory.getLogger(TravelScheduleController.class);
	@Autowired
	private TravelScheduleService travelScheduleService;

	@Autowired
	private TravelScheduleRepository scheduleRepository;
	

	@GetMapping("avalibility")
	public ResponseEntity<TravelScheduleResponseWrapper> getSchedules(
			@RequestParam(value = "sourceCode",required=false) String sourceCode,
			@RequestParam(value = "destinationCode",required=false) String destinationCode, @RequestParam("date") String date) {
		return travelScheduleService.getAvailableSchedule(sourceCode, destinationCode, date);
	}


	

 
	@JsonInclude(value = Include.NON_NULL)
	@PostMapping
	public ResponseEntity<?> createTravelSchedule(@RequestBody TravelScheduleDTO travelScheduleDTO) throws ParseException {
	    ResponseEntity<?> travelSchedule = travelScheduleService.createSchedule(travelScheduleDTO);

	    if (travelSchedule != null) {
	        return ResponseEntity.ok(travelSchedule);
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create travel schedule");
	    }
	}


	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateSchedule(@PathVariable Long id, @RequestBody TravelScheduleDTO updatedSchedule) {
	    try {
	        TravelSchedule savedSchedule = travelScheduleService.updateSchedule(id, updatedSchedule);
	        if (savedSchedule != null) {
	            return ResponseEntity.ok(savedSchedule);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (EntityNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
      Optional<TravelSchedule> existingScheduleOptional = scheduleRepository.findById(id);
      if (existingScheduleOptional.isPresent()) {
          scheduleRepository.deleteById(id);
          return ResponseEntity.noContent().build();
      } else {
          return ResponseEntity.notFound().build();
      }
  }

  
 
  
  
}