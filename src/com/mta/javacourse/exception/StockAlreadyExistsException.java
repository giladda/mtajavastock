package com.mta.javacourse.exception;
/**
 * Exception for adding an existing stock
 * @author Gilad David
 *
 */
public class StockAlreadyExistsException extends Exception {

	public StockAlreadyExistsException(String symbol)
	{
		super ("Stock " + symbol + " already exists in portfolio");
	}	
}
