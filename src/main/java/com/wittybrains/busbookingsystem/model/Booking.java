package com.wittybrains.busbookingsystem.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;

	@OneToOne
	private TravelSchedule schedule;
    @CreationTimestamp
	private LocalDateTime createdOn;
    

	@CreationTimestamp
    private LocalDateTime updatedOn;
    private int numberOfSeats;
    private double seatCost;
    private Double totalAmount;
    //private int totalSeat;
    private int seatBooked;
    private int availableSeat;
	@ManyToOne
	private User user;
	@ElementCollection
	private List<ExtraAddon> extraAddons;
 
    
    
	

	

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}


	public TravelSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(TravelSchedule schedule) {
		this.schedule = schedule;
	}
	@PrePersist
     void prePersist() {
    	 this.createdOn=LocalDateTime.now();
    	 this.updatedOn=LocalDateTime.now();
     }
	
 
   



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	




	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	
	public Double getTotalAmount() {
        return totalAmount;
    }




	public double getSeatCost() {
		return seatCost;
	}

	public void setSeatCost(double seatCost) {
		this.seatCost = seatCost;
	}

	public void setTotalAmount(double totalAmount) {
	 this.totalAmount=totalAmount;
	}

//	public int getTotalSeat() {
//		return totalSeat;
//	}
//
//	public void setTotalSeat(int totalSeat) {
//		this.totalSeat = totalSeat;
//	}

	public int getSeatBooked() {
		return seatBooked;
	}

	public void setSeatBooked(int seatBooked) {
		this.seatBooked = seatBooked;
	}

	public int getAvailableSeat() {
		return availableSeat;
	}

	public void setAvailableSeat(int availableSeat) {
		this.availableSeat = availableSeat;
	}

	public List<ExtraAddon> getExtraAddons() {
		return extraAddons;
	}

	

	public void setExtraAddons(List<ExtraAddon> extraAddOnList) {
	    this.extraAddons = extraAddOnList;
	}


//	public ExtraAddon getExtraAddon() {
//		return extraAddon;
//	}
//
//	public void setExtraAddon(ExtraAddon extraAddon) {
//		this.extraAddon = extraAddon;
//	}



	

	

	
	

}
