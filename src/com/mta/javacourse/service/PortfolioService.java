package com.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;
/**
 * Class for adding values into stock fields
 * 
 * @author Gilad
 *
 */
public class PortfolioService {

	/**
	 * function which makes new portfolio
	 * and returns it
	 * 
	 * @return
	 */
	public Portfolio getPortfolio ()
	{
		Portfolio myPortfolio = new Portfolio();
		
		Calendar c = Calendar.getInstance();
		c.set (2014, 10, 15, 0, 0, 0);
		Date date = c.getTime();
		
		Stock s1 = new Stock("PIH", 12.4f, 13.1f, date);
		Stock s2 = new Stock("AAL", 5.5f, 5.78f, date);
		Stock s3 = new Stock("CAAS", 31.5f, 31.2f, date);

		myPortfolio.addStock(s1);
		myPortfolio.addStock(s2);
		myPortfolio.addStock(s3);
		
		return myPortfolio;
	}
}