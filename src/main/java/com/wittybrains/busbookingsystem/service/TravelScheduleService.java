
package com.wittybrains.busbookingsystem.service;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wittybrains.busbookingsystem.controller.TravelScheduleResponseWrapper;
import com.wittybrains.busbookingsystem.dto.TravelScheduleDTO;
import com.wittybrains.busbookingsystem.exception.UnprocessableEntityException;
import com.wittybrains.busbookingsystem.model.Bus;
import com.wittybrains.busbookingsystem.model.Station;
import com.wittybrains.busbookingsystem.model.TravelSchedule;
import com.wittybrains.busbookingsystem.repository.BusRepository;
import com.wittybrains.busbookingsystem.repository.StationRepository;
import com.wittybrains.busbookingsystem.repository.TravelScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TravelScheduleService {
	private static final Logger logger = LoggerFactory.getLogger(TravelScheduleService.class);
	private static final int MAX_SEARCH_DAYS = 30;
	private final TravelScheduleRepository scheduleRepository;
	private final StationRepository stationRepository;
	@Autowired
	private BusRepository busRepository;

	public TravelScheduleService(TravelScheduleRepository scheduleRepository, StationRepository stationRepository) {
		this.scheduleRepository = scheduleRepository;
		this.stationRepository = stationRepository;
	}

	public Station getStationByCode(String code) {
		Optional<Station> optionalStation = stationRepository.findByStationCode(code);
		return optionalStation.orElse(null);
	}

	public List<TravelSchedule> getTravelSchedulesBySource(Long sourceStationId) {
		Station sourceStation = new Station();
		sourceStation.setId(sourceStationId);

		return scheduleRepository.findBySource(sourceStation);
	}

	public ResponseEntity<TravelScheduleResponseWrapper> getAvailableSchedule(String sourceCode, String destinationCode,
			String date) {

		// Check if date is empty or null
		if (StringUtils.isEmpty(date)) {
			String message = "Date is null or empty. ";
			TravelScheduleResponseWrapper response = new TravelScheduleResponseWrapper(message,
					Collections.emptyList());
			logger.warn(message);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		// Check if destinationCode is empty or null
		if (StringUtils.isEmpty(destinationCode)) {
			String message = "Destination station code is null or empty. ";
			TravelScheduleResponseWrapper response = new TravelScheduleResponseWrapper(message,
					Collections.emptyList());
			logger.warn(message);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		// Check if sourceCode is empty or null
		if (StringUtils.isEmpty(sourceCode)) {
			String message = "Source station code is null or empty.";
			TravelScheduleResponseWrapper response = new TravelScheduleResponseWrapper(message,
					Collections.emptyList());
			logger.warn(message);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		// Check if source and destination stations exist in the database
		Station source = getStationByCode(sourceCode);
		if (source == null) {
			String message = "Invalid source with station code " + sourceCode;
			TravelScheduleResponseWrapper response = new TravelScheduleResponseWrapper(message,
					Collections.emptyList());
			logger.warn(message);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		Station destination = getStationByCode(destinationCode);
		String message = null;

		TravelScheduleResponseWrapper response = new TravelScheduleResponseWrapper(message, Collections.emptyList());

		if (destination == null) {
			message = "Invalid destination with station code " + destinationCode;
			response.setMessage(message);
			logger.warn(message);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		// Check if source and destination stations are the same
		if (sourceCode.equals(destinationCode)) {
			message = "Source and destination station codes cannot be the same. ";
			response.setMessage(message);
			logger.warn(message);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		try {

			LocalDate parsedDate = LocalDate.parse(date);
			List<TravelScheduleDTO> schedules = getAvailableSchedules(source, destination, parsedDate);

			if (schedules.isEmpty()) {
				message = "No schedule is available for the date you searched for.";
				response.setMessage(message);
				logger.info(message);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			} else {
				message = "Available schedules between " + source.getName() + " and " + destination.getName() + " on "
						+ date.toString();
				response.setMessage(message);
				logger.info(message);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

		} catch (DateTimeParseException ex) {
			message = "Invalid date format. The correct format is ISO date format (yyyy-MM-dd) ";

			response.setMessage(message);
			logger.warn(message);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} catch (UnprocessableEntityException ex) {
			message = ex.getMessage();
			response.setMessage(message);
			logger.warn(message);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
		}
	}

	public List<TravelScheduleDTO> getAvailableSchedules(Station source, Station destination, LocalDate searchDate) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDate currentDate = currentDateTime.toLocalDate();
		LocalTime currentTime = currentDateTime.toLocalTime();

		LocalDateTime searchDateTime = LocalDateTime.of(searchDate, LocalTime.MIDNIGHT);
		if (searchDate.isBefore(currentDate)) {
			// cannot search for past schedules
			String message = "Cannot search for schedules in the past";
			logger.warn(message);

			throw new UnprocessableEntityException(message);

		} else if (searchDate.equals(currentDate)) {
			// search for schedules at least 1 hour from now
			searchDateTime = LocalDateTime.of(searchDate, currentTime.plusHours(1));
		}

		LocalDateTime maxSearchDateTime = currentDateTime.plusDays(MAX_SEARCH_DAYS);
		if (searchDateTime.isAfter(maxSearchDateTime)) {
			// cannot search for schedules more than one month in the future
			String message = "Cannot search for schedules more than one month in the future";
			logger.warn(message);
			throw new UnprocessableEntityException(message);

		}

		List<TravelSchedule> travelScheduleList = scheduleRepository
				.findBySourceAndDestinationAndEstimatedArrivalTimeAfter(source, destination, currentDateTime);

		List<TravelScheduleDTO> travelScheduleDTOList = new ArrayList<>();
		for (TravelSchedule travelSchedule : travelScheduleList) {
			TravelScheduleDTO travelScheduleDTO = new TravelScheduleDTO(travelSchedule);
			travelScheduleDTOList.add(travelScheduleDTO);
		}

		if (travelScheduleDTOList.isEmpty()) {
			String message = "No available schedules found for the given search criteria";
			logger.warn(message);
		}

		return travelScheduleDTOList;
	}

	public TravelSchedule updateSchedule(Long id, TravelScheduleDTO updatedSchedule) {
		Optional<TravelSchedule> existingScheduleOptional = scheduleRepository.findById(id);

		if (existingScheduleOptional.isPresent()) {
			TravelSchedule existingSchedule = existingScheduleOptional.get();

			Optional<Bus> busOptional = Optional.ofNullable(busRepository.findById(updatedSchedule.getBus().getId())
					.orElseThrow(() -> new EntityNotFoundException(
							"Bus with id " + updatedSchedule.getBus().getId() + " not found")));

			if (busOptional.isPresent()) {
				Bus bus = busOptional.get();

			//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				// check if the same bus is being used within 24 hours
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
				boolean isSameBusUsedWithin24Hours = scheduleRepository
						.existsByBusIdAndEstimatedDepartureTimeGreaterThanEqual(bus.getId(), LocalDateTime
								.parse(updatedSchedule.getEstimatedArrivalTime(),formatter).plusHours(24).toString());
				
				

				if (isSameBusUsedWithin24Hours) {
					throw new IllegalStateException("Bus with id " + bus.getId() + " cannot be used before 24 hours");
				}

				existingSchedule.setBus(bus);
			}

			existingSchedule.setEstimatedArrivalTime(updatedSchedule.getEstimatedArrivalTime());
			existingSchedule.setEstimatedDepartureTime(updatedSchedule.getEstimatedDepartureTime());
			existingSchedule.setTotalSeat(updatedSchedule.getTotalSeat());
			existingSchedule.setSeatBooked(updatedSchedule.getSeatBooked());
			existingSchedule.setSeatCost(updatedSchedule.getSeatCost());

			TravelSchedule savedSchedule = scheduleRepository.save(existingSchedule);
			return savedSchedule;
		} else {
			return null;
		}
	}

//	 public TravelSchedule updateSchedule(Long id, TravelScheduleDTO updatedSchedule) {
//	        Optional<TravelSchedule> existingScheduleOptional = scheduleRepository.findById(id);
//	        
//	        if (existingScheduleOptional.isPresent()) {
//	            TravelSchedule existingSchedule = existingScheduleOptional.get();
//	            
//	            Optional<Bus> busOptional = Optional.ofNullable(busRepository.findById(updatedSchedule.getBus().getId())
//	                    .orElseThrow(() -> new EntityNotFoundException("Bus with id " + updatedSchedule.getBus().getId() + " not found")));
//	            
//	            if (busOptional.isPresent()) {
//	                Bus bus = busOptional.get();
//	                existingSchedule.setBus(bus);
//	            }
//	            
//	            existingSchedule.setEstimatedArrivalTime(updatedSchedule.getEstimatedArrivalTime());
//	            existingSchedule.setEstimatedDepartureTime(updatedSchedule.getEstimatedDepartureTime());
//	            existingSchedule.setTotalSeat(updatedSchedule.getTotalSeat());
//	            existingSchedule.setSeatBooked(updatedSchedule.getSeatBooked());
//	            existingSchedule.setSeatCost(updatedSchedule.getSeatCost());
//	            
//	            TravelSchedule savedSchedule = scheduleRepository.save(existingSchedule);
//	            return savedSchedule;
//	        } else {
//	            return null;
//	        }
//	    }

	public ResponseEntity<?> createSchedule(TravelScheduleDTO travelScheduleDTO) throws ParseException {
		logger.info("Creating travel schedule: {}", travelScheduleDTO);

		// Check if the same travel schedule already exists
		List<TravelSchedule> existingSchedule = scheduleRepository
				.findBySourceAndDestinationAndBusIdAndEstimatedDepartureTimeAndEstimatedArrivalTime(
						travelScheduleDTO.getSource(), travelScheduleDTO.getDestination(),
						travelScheduleDTO.getBus().getId(), travelScheduleDTO.getEstimatedDepartureTime(),
						travelScheduleDTO.getEstimatedArrivalTime());

		if (existingSchedule != null && !existingSchedule.isEmpty()) {
			logger.warn("Travel schedule already exists: {}", existingSchedule);
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Travel schedule already exists");
		}

		TravelSchedule travelSchedule = new TravelSchedule();

		travelSchedule.setSource(travelScheduleDTO.getSource());
		travelSchedule.setDestination(travelScheduleDTO.getDestination());
		travelSchedule.setSeatCost(travelScheduleDTO.getSeatCost());
		travelSchedule.setTotalSeat(travelScheduleDTO.getTotalSeat());
		travelSchedule.setEstimatedArrivalTime(travelScheduleDTO.getEstimatedArrivalTime());
		travelSchedule.setEstimatedDepartureTime(travelScheduleDTO.getEstimatedDepartureTime());
		travelSchedule.setBus(travelScheduleDTO.getBus());

		// Calculate the number of available seats and the number of seats already
		// booked
		int totalSeats = travelSchedule.getTotalSeat();
		int seatsBooked = travelSchedule.getSeatBooked();
		int availableSeats = totalSeats - seatsBooked;

		// Update the number of seats booked and available seats based on the number of
		// seats being booked
		int requestedSeatCount = travelScheduleDTO.getSeatBooked();
		if (requestedSeatCount > 0 && requestedSeatCount <= availableSeats) {
			seatsBooked += requestedSeatCount;
			availableSeats = availableSeats - requestedSeatCount;
			travelSchedule.setSeatBooked(seatsBooked);
			travelSchedule.setAvailableSeat(availableSeats);
			travelSchedule = scheduleRepository.save(travelSchedule);
			logger.info("Created travel schedule: {}", travelSchedule);
			return ResponseEntity.status(HttpStatus.CREATED).body(travelSchedule);
		} else {
			logger.warn("Cannot book {} seats, only {} seats are available", requestedSeatCount, availableSeats);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					"Cannot book " + requestedSeatCount + " seats, only " + availableSeats + " seats are available");
		}
	}
}