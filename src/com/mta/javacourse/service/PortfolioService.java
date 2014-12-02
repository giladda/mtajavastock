package com.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;

public class PortfolioService {

	Stock s1 = new Stock();
	Stock s2 = new Stock();
	Stock s3 = new Stock();

	public Portfolio getPortfolio ()
	{
		Portfolio myPortfolio = new Portfolio();
		Calendar c = Calendar.getInstance();
		c.set (2014, 10, 15, 0, 0, 0);
		Date date = c.getTime();
		
		s1.setSymbol("PIH");
		s1.setAsk(12.4f);
		s1.setBid(13.1f);
		s1.setDate(date);

		s2.setSymbol("AAL");
		s2.setAsk(5.5f);
		s2.setBid(5.78f);
		s2.setDate(date);

		s3.setSymbol("CAAS");
		s3.setAsk(31.5f);
		s3.setBid(31.2f);
		s3.setDate(date);

		myPortfolio.addStock(s1);
		myPortfolio.addStock(s2);
		myPortfolio.addStock(s3);
		
		return myPortfolio;
	}
}