package com.mta.javacourse.model;

import java.util.Date;

/**
 * Class for setting stock fields
 * 
 * @author Gilad David
 *
 */
public class Stock {

	//Data Members
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
/**
 * c'tor for initializing stocks
 */
	public Stock()
	{
		symbol = "";
		ask = 0;
		bid = 0;
		date = new Date();
	}

	/**
	 * c'tor for filling stock fields with values
	 * 
	 * @param symbol
	 * @param ask
	 * @param bid
	 * @param date
	 */
	public Stock (String symbol, float ask, float bid, Date date)
	{
		this();
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);
		setDate(date);
	}

	/**
	 * copy c'tor for stock
	 * @param stock
	 */
	public Stock (Stock stock)
	{
		this (stock.getSymbol(), stock.getAsk(), stock.getBid(), stock.getDate());
	}

	//Setters and Getters--->

	//Symbol S&G
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	//Ask S&G
	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	//Bid S&G
	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	//Date S&G
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHtmlDescription()
	{
		String stockHtmlDetailsString = "<b>Stock symbol</b>: " +getSymbol() + "<b> Stock Ask</b>: " +getAsk() + "<b> Bid</b>: " +getBid() + "<b> Stock Date</b>: " +getDate();
		return stockHtmlDetailsString;
	}
}
