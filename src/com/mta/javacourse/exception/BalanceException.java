package com.mta.javacourse.exception;
/**
 * Exception for preventing negative balance issues
 * @author Gilad David
 *
 */
public class BalanceException extends Exception {

	private static final long serialVersionUID = 1L;

	public BalanceException(float portfolioBalance)
	{
		super("your portfolio balance: " + "$" + portfolioBalance + " is not enough to make this purchase");
	}
}