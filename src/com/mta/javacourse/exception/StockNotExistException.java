package com.mta.javacourse.exception;
/**
 * Stock not found in Portfolio exception
 * @author Gilad David
 *
 */
public class StockNotExistException extends Exception {

	public StockNotExistException(String symbol)
	{
		super("Stock " + symbol + " doesn't exist in portfolio");
	}
}
