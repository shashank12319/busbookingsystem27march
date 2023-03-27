package com.wittybrains.busbookingsystem.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wittybrains.busbookingsystem.model.Booking;
import com.wittybrains.busbookingsystem.model.ExtraAddon;



@JsonInclude(value = Include.NON_NULL)

public class BookingDTO {
    private Long bookingId;

    private TravelScheduleDTO schedule;
    
    private int numberOfSeats;
 
    private Double totalAmount;
    private BusDTO bus;
    private UserDTO user;
    private String seatStatus;
    //private int availableSeat;
    //private int totalSeat;
   // private int seatBooked;
    private List<ExtraAddon> extraAddons;


    public BookingDTO() {
        super();
    }

    public UserDTO getUser() {
        return user;
    }

    public BookingDTO(Booking booking) {
        this.bookingId = booking.getBookingId();
        this.schedule = new TravelScheduleDTO(booking.getSchedule());
        //this.setTotalSeat(booking.getTotalSeat());
        //this.seatBooked=booking.getSeatBooked();
        this.numberOfSeats=booking.getNumberOfSeats();
        this.user = new UserDTO(booking.getUser());
        this.totalAmount=booking.getTotalAmount();
       // this.availableSeat=booking.getAvailableSeat();
    }
       

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public TravelScheduleDTO getSchedule() {
        return schedule;
    }

    public void setSchedule(TravelScheduleDTO schedule) {
        this.schedule = schedule;
    }

    public BusDTO getBus() {
        return bus;
    }

    public void setBus(BusDTO bus) {
        this.bus = bus;
    }

    public void setUser(UserDTO userDTO) {
        this.user = userDTO;
    }

    

    public String getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(String bookingStatus) {
        this.seatStatus = bookingStatus;
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
//
//	public int getSeatBooked() {
//		return seatBooked;
//	}
//
//	public void setSeatBooked(int seatBooked) {
//		this.seatBooked = seatBooked;
//	}

//	public int getAvailableSeat() {
//		return availableSeat;
//	}
//
//	public void setAvailableSeat(int availableSeat) {
//		this.availableSeat = availableSeat;
//	}

//	public int getTotalSeat() {
//		return totalSeat;
//	}
//
//	public void setTotalSeat(int totalSeat) {
//		this.totalSeat = totalSeat;
//	}

//	public int getAvailableSeat() {
//		return availableSeat;
//	}
//
//	public void setAvailableSeat(int availableSeat) {
//		this.availableSeat = availableSeat;
//	}

	public List<ExtraAddon> getExtraAddons() {
		return extraAddons;
	}

	public void setExtraAddons(List<ExtraAddon> extraAddons) {
		this.extraAddons = extraAddons;
	}

	
//	public ExtraAddon getExtraAddon() {
//		return extraAddon;
//	}
//
//	public void setExtraAddon(ExtraAddon extraAddon) {
//		this.extraAddon = extraAddon;
//	}

	
	
//	public int getAvailableSeat() {
//		return availableSeat;
//	}
//
//	public void setAvailableSeat(int availableSeat) {
//		this.availableSeat = availableSeat;
//	}



}
