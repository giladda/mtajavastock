package com.mta.javacourse.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;
import com.mta.javacourse.model.StockStatus;
/**
 * Class for adding values into stock fields
 * 
 * @author Gilad David
 *
 */
public class PortfolioService {

	/**
	 * function which generates new portfolio
	 * and returns it
	 * 
	 * @return
	 */
	public Portfolio getPortfolio ()
	{
		Portfolio myPortfolio = new Portfolio("portfolio");

		myPortfolio.setTitle("Exercise 8 portfolio");
		myPortfolio.setBalance(10000);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.ERA, GregorianCalendar.AD);
		cal.set(2014,11,15);
		java.util.Date date = cal.getTime();

		Stock s1 = new StockStatus("PIH", 10f, 8.5f, date);
		Stock s2 = new StockStatus("AAL", 30f, 25.5f, date);
		Stock s3 = new StockStatus("CAAS", 20f, 15.5f, date);

		myPortfolio.addStock(s1);
		myPortfolio.addStock(s2);
		myPortfolio.addStock(s3);

		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);

		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");

		return myPortfolio;
	}
}