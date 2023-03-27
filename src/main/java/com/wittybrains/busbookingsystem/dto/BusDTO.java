package com.wittybrains.busbookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wittybrains.busbookingsystem.model.Bus;
@JsonInclude(value = Include.NON_NULL)
public class BusDTO {
	//private String number;
//    private String type;
    private Long id;
  
    public BusDTO() {
    }
    public BusDTO(Bus busDTO) {
		super();
		//this.id=busDTO.getId();
//		this.number=busDTO.getNumber();
//		this.type = busDTO.getType();
		
		
    }


//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public String getNumber() {
//		return number;
//	}
//	public void setNumber(String number) {
//		this.number = number;
//	}
//	

	

	
	public void setId(Long id) {
		this.id = id;
	}


	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

}