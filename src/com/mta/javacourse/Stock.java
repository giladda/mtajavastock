package com.mta.javacourse;

import java.util.Date;

public class Stock {
	
	//Data Members
	private String Symbol;
	private float Ask;
	private float Bid;
	private Date myDate;
	
	//Setters and Getters--->
	
	//Symbol S&G
	public String getSymbol() {
		return Symbol;
	}
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
	
	//Ask S&G
	public float getAsk() {
		return Ask;
	}
	public void setAsk(float ask) {
		Ask = ask;
	}
	
	//Bid S&G
	public float getBid() {
		return Bid;
	}
	public void setBid(float bid) {
		Bid = bid;
	}
	
	//Date S&G
	public Date getDate() {
		return myDate;
	}
	public void setDate(Date date) {
		myDate = date;
	}
	
	public String getHtmlDescription()
	{
		String stockHtmlDetailsString = "<b>Stock symbol</b>: " +getSymbol() + "<b> Stock Ask</b>: " +getAsk() + "<b> Bid</b>: " +getBid() + "<b> Stock Date</b>: " +getDate();
		return stockHtmlDetailsString;
	}
}