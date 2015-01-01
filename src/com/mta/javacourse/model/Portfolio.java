package com.mta.javacourse.model;

import java.util.Date;

/**
 * Class for creating a stock portfolio
 * 
 * @author Gilad David
 *
 */
public class Portfolio {

	public final static int MAX_PORFOLIO_SIZE = 5;
	public enum ALGO_RECOMMENDATION {DO_NOTHING, BUY, SELL};

	private String title;
	private int portfolioSize;
	private float balance;
	private StockStatus[] stockStatus;

	/**
	 * c'tor for initializing portfolio members
	 */
	public Portfolio() {
		portfolioSize = 0;
		balance = 0;
		setTitle("");
		stockStatus = new StockStatus[MAX_PORFOLIO_SIZE];
	}

	/**
	 * cto'r which receives a title and calls 1st cto'r for setting it
	 * 
	 * @param title
	 */
	public Portfolio(String title) {
		this();
		this.setTitle(title);
	}

	/**
	 * copy c'tor for making instance copies of Portfolio object
	 * 
	 * @param portfolio
	 */
	public Portfolio(Portfolio portfolio) {
		this(portfolio.getTitle());
		setPortfolioSize(portfolio.getPortfolioSize());

		for (int i = 0; i < portfolioSize; i++)
			stockStatus[i] = new StockStatus(portfolio.getStocksStatus()[i]);
	}

	/**
	 * function for adding new stock to portfolio within portfolio size limits
	 * 
	 * @param stock
	 */
	public void addStock(Stock stock) {
		for (int i = 0; i < portfolioSize; i++)
			if (stock.getSymbol().equals(stockStatus[i].getSymbol()))
			{
				System.out.println("Stock Already Exists in Portfolio");
				return;
			}

		if (portfolioSize < MAX_PORFOLIO_SIZE) 
		{
			this.stockStatus[portfolioSize] = new StockStatus();
			this.stockStatus[portfolioSize].setSymbol(stock.getSymbol());
			this.stockStatus[portfolioSize].setAsk(stock.getAsk());
			this.stockStatus[portfolioSize].setBid(stock.getBid());
			this.stockStatus[portfolioSize].setDate(new Date(stock.date.getTime()));
			portfolioSize++;
		} else
			System.out.println("Can't add new stock, Portfolio can have only " + portfolioSize + " stocks");
	}

	/**
	 * function for removing a specific stock from portfolio (according to its symbol)
	 * 
	 * @param stock
	 */
	public boolean removeStock(String symbol) {
		sellStock(symbol, -1);
		for (int i = 0; i < portfolioSize; i++)
			if (symbol.equals(stockStatus[i].getSymbol()))
			{
				stockStatus[i] = stockStatus[portfolioSize - 1];
				stockStatus[portfolioSize - 1] = null;
				stockStatus[i] = stockStatus[portfolioSize - 1];
				stockStatus[portfolioSize - 1] = null;
				portfolioSize--;
				return true;
			} 
		return false;
	}
	/**
	 * function for selling stocks and updating portfolio balance
	 * 
	 * @param symbol
	 * @param quantity
	 * @return
	 */
	public boolean sellStock (String symbol, int quantity)
	{
		for (int i = 0; i < portfolioSize; i++)
			if (symbol.equals(stockStatus[i].getSymbol()))
			{
				if (quantity == -1)
				{
					float sellProfit = stockStatus[i].getStockQuantity()*stockStatus[i].getBid();
					updateBalance(sellProfit);
					stockStatus[i].setStockQuantity(0);
				}
				else if ((stockStatus[i].getStockQuantity()-quantity) >= 0)
				{
					stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()-quantity);
					float sellProfit1 = stockStatus[i].getStockQuantity() * stockStatus[i].getBid();
					updateBalance(sellProfit1);
				}
				else if ((stockStatus[i].getStockQuantity()-quantity < 0))
					System.out.println("Error->Your Stock Quantity is lower than requested");
				return true;
			}
		return false;
	}
	/**
	 * function for buying stocks and updating portfolio balance
	 * 
	 * @param symbol
	 * @param quantity
	 * @return
	 */
	public boolean buyStock (String symbol, int quantity)
	{
		for (int i = 0; i < portfolioSize; i++)
			if (symbol.equals(stockStatus[i].getSymbol()))
			{
				if (quantity == -1)
				{
					int amountToBuy = (int)(balance/stockStatus[i].getAsk());
					stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()+amountToBuy);
					float buyValue = (stockStatus[i].getStockQuantity() * stockStatus[i].getAsk()) * (-1);
					updateBalance(buyValue);
				}
				else if (quantity >= 1)
				{
					stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()+quantity);
					float buyValueX = (quantity * stockStatus[i].getAsk()) * (-1);
					updateBalance(buyValueX);
				}
				return true;
			}
		return false;
	}

	/**
	 * function for updating balance when buying/selling stocks
	 * 
	 * @param amount
	 */
	public void updateBalance(float amount) {
		balance += amount;
	}

	/**
	 * function for returning total value of all stocks
	 * @return
	 */
	public float getStocksValue()
	{
		float res = 0;
		for (int i = 0; i < portfolioSize; i++)
			res+=stockStatus[i].getStockQuantity() * stockStatus[i].getBid();
		return res;
	}
	/**
	 * function for returning total portfolio value (balance+stocks value)
	 * 
	 * @return
	 */
	public float getTotalValue()
	{
		return (getBalance() + getStocksValue());
	}

	/**
	 * creates html string from all stocks in portfolio
	 * 
	 * @return
	 */
	public String getHtmlString() {
		String res = "<h1><center>" + getTitle() + "</center></h1>" + "<br>";
		res+="<b> Total Portfolio Value: </b>" + getTotalValue() + "$ <b>Total Stocks Value: </b>" + getStocksValue() + "$" + " <b>Balance:</b> " + getBalance() +"$" + "<br>" + "<br>" + "<b>Portfolio Stocks:</b>" + "<br>";

		int i = 0;
		while (i < portfolioSize) {
			res += stockStatus[i].getHtmlDescription() + "<br>";
			i++;
		}
		return res;
	}

	// Setters & Getters-->

	public StockStatus[] getStocksStatus() {
		return stockStatus;
	}

	public void setStockStatus(StockStatus[] stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public float getBalance()
	{
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
}