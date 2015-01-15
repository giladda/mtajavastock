package com.mta.javacourse.exception;
/**
 * Stock not found in Portfolio exception
 * @author Gilad David
 *
 */
public class StockNotExistException extends Exception {

	public StockNotExistException()
	{
		super("Stock doesn't exist in portfolio");
	}
}
