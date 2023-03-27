package com.wittybrains.busbookingsystem.exception;
public class TravelScheduleNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TravelScheduleNotFoundException(String message) {
        super(message);
    }
}