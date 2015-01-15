package com.mta.javacourse.exception;
/**
 * Exception for preventing negative balance issues
 * @author Gilad David
 *
 */
public class BalanceException extends Exception {

	public BalanceException()
	{
		super("Insufficient Funds");
	}
}