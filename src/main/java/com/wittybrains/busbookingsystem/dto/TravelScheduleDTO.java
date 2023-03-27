package com.wittybrains.busbookingsystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wittybrains.busbookingsystem.model.Bus;
import com.wittybrains.busbookingsystem.model.TravelSchedule;



@JsonInclude(value = Include.NON_NULL)
public class TravelScheduleDTO {

//	private StationDTO source;
//	private StationDTO destination;
	
	private String estimatedArrivalTime;
	private String estimatedDepartureTime;
	//private LocalDateTime estimatedArrivalTime;
   // private LocalDateTime estimatedDepartureTime;
	
	private String source;
	private String destination;
	private Long id;
    private Bus bus;
    private int seatBooked;
    private int totalSeat;
    private double SeatCost;
    private int  availableSeat;
	public TravelScheduleDTO() {
		super();
	}

	public TravelScheduleDTO(TravelSchedule travelSchedule) {
		super();
		if (travelSchedule != null) {


			
			this.source=travelSchedule.getSource();
            this.totalSeat=travelSchedule.getTotalSeat();
			this.estimatedArrivalTime = travelSchedule.getEstimatedArrivalTime().toString();
			this.estimatedDepartureTime = travelSchedule.getEstimatedDepartureTime().toString();
            
            //this.setEstimatedArrivalTime(travelSchedule.getEstimatedArrivalTime());
            //this.setEstimatedDepartureTime(travelSchedule.getEstimatedDepartureTime());
			this.availableSeat=travelSchedule.getAvailableSeat();
			this.setSeatBooked(travelSchedule.getSeatBooked());
			this.setBus(travelSchedule.getBus());
			this.SeatCost=travelSchedule.getSeatCost();
		}

	}

	public TravelScheduleDTO(String string) {
		// TODO Auto-generated constructor stub
	}

	



	public String getEstimatedArrivalTime() {
		return estimatedArrivalTime;
	}

	public void setEstimatedArrivalTime(String estimatedArrivalTime) {
		this.estimatedArrivalTime = estimatedArrivalTime;
	}

	public String getEstimatedDepartureTime() {
		return estimatedDepartureTime;
	}

	public void setEstimatedDepartureTime(String estimatedDepartureTime) {
		this.estimatedDepartureTime = estimatedDepartureTime;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public int getAvailableSeat() {
		return availableSeat;
	}

	public void setAvailableSeat(int availableSeat) {
		this.availableSeat = availableSeat;
	}

	public int getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}

//	public double getSeatCost() {
//		return seatCost;
//	}
//
//	public void setSeatCost(double seatCost) {
//		this.seatCost = seatCost;
//	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getSeatBooked() {
		return seatBooked;
	}

	public void setSeatBooked(int seatBooked) {
		this.seatBooked = seatBooked;
	}

	public double getSeatCost() {
		return SeatCost;
	}

	public void setSeatCost(double seatCost) {
		SeatCost = seatCost;
	}

//	public CharSequence getEstimatedArrivalTime() {
//		return estimatedArrivalTime;
//	}
//
//	public void setEstimatedArrivalTime(CharSequence estimatedArrivalTime) {
//		this.estimatedArrivalTime = estimatedArrivalTime;
//	}
//
//	public CharSequence getEstimatedDepartureTime() {
//		return estimatedDepartureTime;
//	}
//
//	public void setEstimatedDepartureTime(CharSequence estimatedDepartureTime) {
//		this.estimatedDepartureTime = estimatedDepartureTime;
//	}

//	public LocalDateTime getEstimatedArrivalTime() {
//		return estimatedArrivalTime;
//	}
//
//	public void setEstimatedArrivalTime(LocalDateTime estimatedArrivalTime) {
//		this.estimatedArrivalTime = estimatedArrivalTime;
//	}
//
//	public LocalDateTime getEstimatedDepartureTime() {
//		return estimatedDepartureTime;
//	}
//
//	public void setEstimatedDepartureTime(LocalDateTime estimatedDepartureTime) {
//		this.estimatedDepartureTime = estimatedDepartureTime;
//	}


	

	

	

}