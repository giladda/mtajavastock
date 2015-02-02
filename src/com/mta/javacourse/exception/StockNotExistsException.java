package com.mta.javacourse.exception;
/**
 * Stock not found in Portfolio exception
 * @author Gilad David
 *
 */
public class StockNotExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockNotExistsException(String symbol)
	{
		super("Stock " + symbol + " doesn't exist in portfolio");
	}
}
