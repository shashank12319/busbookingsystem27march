package com.wittybrains.busbookingsystem.exception;

public class NoScheduleFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoScheduleFoundException(String message) {
        super(message);
    }
}