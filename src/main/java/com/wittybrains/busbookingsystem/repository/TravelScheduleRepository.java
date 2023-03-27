package com.wittybrains.busbookingsystem.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wittybrains.busbookingsystem.model.Bus;
import com.wittybrains.busbookingsystem.model.Station;
import com.wittybrains.busbookingsystem.model.TravelSchedule;

public interface TravelScheduleRepository extends JpaRepository<TravelSchedule, Long> {
	@Query(nativeQuery = true, value = "Select ts.* from travel_schedule ts WHERE source_id = :source AND destination_id = :destination AND estimated_arrival_time > :estimatedArrivalTime")
	List<TravelSchedule> findBySourceAndDestinationAndEstimatedArrivalTimeAfter(String source, String destination,
			LocalDateTime estimatedArrivalTime);
    
	List<TravelSchedule> findBySourceAndDestinationAndEstimatedArrivalTimeAfter(Station source, Station destination,
			LocalDateTime currentDateTime);
  
	List<TravelSchedule> findBySource(Station source);

	@Query(nativeQuery = true, value = "Select ts.* from travel_schedule ts WHERE source_id = :sourceId")
	List<TravelSchedule> findBySource(@Param("sourceId") Long sourceId);

	List<TravelSchedule> findByEstimatedArrivalTimeBefore(LocalDateTime localDateTime);

	@Query(nativeQuery = true, value = "select ts.* from travel_schedule ts WHERE ts.estimated_arrival_time < :localDateTime")
	List<TravelSchedule> findByEstimatedArrivalTime(LocalDateTime localDateTime);


    
	
    List<TravelSchedule> findBySourceAndDestinationAndBusIdAndEstimatedDepartureTimeAndEstimatedArrivalTime(
            String source, String destination, Long busId, String estimatedDepartureTime, String estimatedArrivalTime);

	boolean existsByBusIdAndEstimatedDepartureTimeGreaterThanEqual(Long busId, String plusHours);

	List<TravelSchedule> findBySourceAndDestinationAndBusIdAndEstimatedDepartureTimeAndEstimatedArrivalTime(
			String source, String destination, Long id, LocalDateTime estimatedDepartureTime,
			LocalDateTime estimatedArrivalTime);

	List<TravelSchedule> findBySourceAndDestinationAndBusIdAndEstimatedDepartureTimeAndEstimatedArrivalTime(
			String source, String destination, Long id, CharSequence estimatedDepartureTime,
			CharSequence estimatedArrivalTime);
}
