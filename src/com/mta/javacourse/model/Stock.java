package com.mta.javacourse.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class for setting stock fields
 * 
 * @author Gilad David
 *
 */
public class Stock {

	// Data Members
	protected String symbol;
	protected float ask;
	protected float bid;
	protected Calendar cal = Calendar.getInstance();
	protected java.util.Date date = cal.getTime(); 

	DateFormat dateFt = new SimpleDateFormat("dd/MM/yy");

	/**
	 * c'tor for initializing stocks
	 */
	public Stock() {
		this.symbol = "";
		this.ask = 0;
		this.bid = 0;
	}

	/**
	 * c'tor for filling stock fields with values
	 * 
	 * @param symbol
	 * @param ask
	 * @param bid
	 * @param date
	 */
	public Stock(String symbol, float ask, float bid, Date date) {
		this();
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);
		setDate(new Date(date.getTime()));
	}

	/**
	 * copy c'tor for stock
	 * 
	 * @param stock
	 */
	public Stock(Stock stock) {
		this(stock.getSymbol(), stock.getAsk(), stock.getBid(), new Date (stock.date.getTime()));
	}

	// Setters and Getters--->

	// Symbol S&G
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	// Ask S&G
	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	// Bid S&G
	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	// Date S&G
	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}
	/**
	 * function for creating html string for a single stock
	 * 
	 * @return
	 */
	public String getHtmlDescription() {
		String dateStr = dateFt.format(date.getTime());
		String stockHtmlDetailsString = "<b>Stock symbol</b>: " + getSymbol()
				+ "<b> Stock Ask</b>: " + getAsk()+ "$" + "<b> Bid</b>: " + getBid()
				+ "$" +"<b> Stock Date</b>: " + dateStr;
		return stockHtmlDetailsString;
	}
}