package com.wittybrains.busbookingsystem.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
@Entity
@Table(name="travelschedule")
public class TravelSchedule {
   

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne
    private Bus bus;

//    @ManyToOne
//    private Station source;
//
//    @ManyToOne
//    private Station destination;
    
    private String source;
	private String destination;
    
    private int availableSeat;
    private int seatBooked;
    private int totalSeat;
    private String estimatedArrivalTime;
   private String estimatedDepartureTime;
    //private LocalDateTime estimatedArrivalTime;
    ///private LocalDateTime estimatedDepartureTime;
   
    private double seatCost;
   
    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

//    public Station getSource() {
//        return source;
//    }
//
//    public void setSource(Station source) {
//        this.source = source;
//    }
//
//    public Station getDestination() {
//        return destination;
//    }
//
//    public void setDestination(Station destination) {
//        this.destination = destination;
//    }


 

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

	public double getSeatCost() {
		return seatCost;
	}

	public void setSeatCost(double seatCost) {
		this.seatCost = seatCost;
	}

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

	
}

