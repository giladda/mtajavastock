package com.mta.javacourse.exception;

/**
 * Exception for full portfolio situations
 * @author Gilad David
 *
 */
public class PortfolioFullException extends Exception {
	public PortfolioFullException()
	{
		super("Warning - You have reached maximum portfolio size!");
	}
}