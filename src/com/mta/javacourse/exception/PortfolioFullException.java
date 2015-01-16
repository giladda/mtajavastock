package com.mta.javacourse.exception;

/**
 * Exception for full portfolio situations
 * @author Gilad David
 *
 */
public class PortfolioFullException extends Exception {
	public PortfolioFullException(int maxPortfolioSize)
	{
		super("you can have maximum " + maxPortfolioSize + " stocks in your portfolio");
	}
}