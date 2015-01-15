package com.mta.javacourse.exception;
/**
 * Exception for adding an existing stock
 * @author Gilad David
 *
 */
public class StockAlreadyExistsException extends Exception {

	public StockAlreadyExistsException()
	{
		super ("Stock already exists in portfolio");
	}	
}
