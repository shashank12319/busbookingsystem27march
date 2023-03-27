//package com.wittybrains.busbookingsystem.model;
//
//import java.time.LocalDateTime;
//
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.wittybrains.busbookingsystem.repository.StationRepository;
//import com.wittybrains.busbookingsystem.repository.TravelScheduleRepository;
//import com.wittybrains.busbookingsystem.repository.UserRepository;
//
//@Component
//public class Test {
//@Autowired
//private TravelScheduleRepository travelScheduleRepository;
//@Autowired
//private StationRepository stationRepository;
//@Autowired
//private UserRepository userRepository;
//@PostConstruct
//void init () {
//	final String source = null;
//	final String destination = null;
//	
//    travelScheduleRepository.findByEstimatedArrivalTimeBefore(LocalDateTime.now());
//    System.out.println("here");
//    travelScheduleRepository.findByEstimatedArrivalTime(LocalDateTime.now());
//    System.out.println("here2");
//
//	travelScheduleRepository.findBySourceAndDestinationAndEstimatedArrivalTimeAfter(source, destination, LocalDateTime.now());
//	System.out.println("here3");
//	String stationCode=null;
//    stationRepository.findByStationCode(stationCode);
//    System.out.println("here4");
//    
//    String findByUsername = "kumar";
//	userRepository.findByUsername(findByUsername);
//	System.out.println("here5");
//	
//	
//	
//	
//	
//
//}
//
//
//}
//
