package com.wittybrains.busbookingsystem.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="bus")
public class Bus {
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    //private String number;
	   // private String type;
        @OneToOne
        private User driver;
       

		public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

//	    public String getNumber() {
//	        return number;
//	    }
//
//	    public void setNumber(String number) {
//	        this.number = number;
//	    }
//
//	    public String getType() {
//	        return type;
//	    }
//
//	    public void setType(String type) {
//	        this.type = type;
//	    }



	}
