package com.wittybrains.busbookingsystem.dto;

import java.util.List;

import com.wittybrains.busbookingsystem.model.Passenger;
import com.wittybrains.busbookingsystem.model.User;


public class UserDTO {
    private Long id;
    private String name;
    private String email;
    //private List<Passenger> passenger;

    public UserDTO() {}

    public UserDTO(User user) {
    	if(user!=null) {
        this.id = user.getUserId();
        this.name = user.getUsername();
        this.email = user.getEmail();
       // this.setPassenger(user.getPassenger());

    }
    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public List<Passenger> getPassenger() {
//		return passenger;
//	}
//
//	public void setPassenger(List<Passenger> passenger) {
//		this.passenger = passenger;
//	}

	
}

