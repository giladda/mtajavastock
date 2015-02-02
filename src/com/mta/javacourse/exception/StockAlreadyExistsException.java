package com.mta.javacourse.exception;
/**
 * Exception for adding an existing stock
 * @author Gilad David
 *
 */
public class StockAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockAlreadyExistsException(String symbol)
	{
		super ("Stock " + symbol + " already exists in portfolio");
	}	
}
