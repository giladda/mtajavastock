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
	private enum ALGO_RECOMMENDATION {DO_NOTHING, BUY, SELL};

	private String title;
	private int portfolioSize;
	private float balance;
	private Stock[] stocks;
	private StockStatus[] stocksStatus;

	/**
	 * c'tor for initiallizing portfolio members
	 */
	public Portfolio() {
		portfolioSize = 0;
		balance = 0;
		setTitle("");
		stocks = new Stock[MAX_PORFOLIO_SIZE];
		stocksStatus = new StockStatus[MAX_PORFOLIO_SIZE];
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
			stocks[i] = new Stock(portfolio.getStocks()[i]);

		for (int i = 0; i < portfolioSize; i++)
			stocksStatus[i] = new StockStatus(portfolio.getStocksStatus()[i]);
	}

	/**
	 * function for adding new stock to portfolio within portfolio size limits
	 * 
	 * @param stock
	 */
	public void addStock(Stock stock) {
		if (portfolioSize < MAX_PORFOLIO_SIZE) 
		{
			stocks[portfolioSize] = stock;
			stocksStatus[portfolioSize] = new StockStatus(stock);
			portfolioSize++;
		} else
			System.out.println("Can't add new stock, Portfolio can have only " + portfolioSize + " stocks");
	}

	/**
	 * function for removing a specific stock from portfolio (according to it's symbol)
	 * 
	 * @param stock
	 */
	public boolean removeStock(String symbol) {
		sellStock(symbol, -1);
		for (int i = 0; i < portfolioSize; i++)
			if (symbol.equals(stocks[i].getSymbol()))
			{
				stocks[i] = stocks[portfolioSize - 1];
				stocks[portfolioSize - 1] = null;
				stocksStatus[i] = stocksStatus[portfolioSize - 1];
				stocksStatus[portfolioSize - 1] = null;
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
			if (symbol.equals(stocks[i].getSymbol()))
			{
				if (quantity == -1)
				{
					float sellProfit = stocksStatus[i].getStockQuantity()*stocksStatus[i].getCurrentBid();
					updateBalance(sellProfit);
					stocksStatus[i].setStockQuantity(0);
				}
				else if ((stocksStatus[i].getStockQuantity()-quantity) >= 0)
				{
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()-quantity);
					float sellProfit1 = stocksStatus[i].getStockQuantity() * stocksStatus[i].getCurrentBid();
					updateBalance(sellProfit1);
				}
				else if ((stocksStatus[i].getStockQuantity()-quantity < 0))
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
			if (symbol.equals(stocks[i].getSymbol()))
			{
				if (quantity == -1)
				{
					int amountToBuy = (int)(balance/stocksStatus[i].getCurrentAsk());
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()+amountToBuy);
					float buyValue = (stocksStatus[i].getStockQuantity() * stocksStatus[i].getCurrentAsk()) * (-1);
					updateBalance(buyValue);
				}
				else if (quantity >= 1)
				{
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()+quantity);
					float buyValueX = (quantity * stocksStatus[i].getCurrentAsk()) * (-1);
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
			res+=stocksStatus[i].getStockQuantity() * stocksStatus[i].getCurrentBid();
		return res;
	}
	/**
	 * function for retuning total portfolio value (balance+stocks value)
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
			res += stocks[i].getHtmlDescription() + "<br>";
			i++;
		}
		return res;
	}

	// Setters & Getters-->

	public Stock[] getStocks() {
		return stocks;
	}

	public StockStatus[] getStocksStatus() {
		return stocksStatus;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
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

	/**
	 * an inner class for storing stock status
	 * 
	 * @author Gilad David
	 *
	 */
	public class StockStatus {

		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private ALGO_RECOMMENDATION recommendation;
		private int stockQuantity;

		/**
		 * 
		 * c'tor for initializing stock status fields
		 */
		public StockStatus() {
			symbol = "empty";
			currentAsk = 0;
			currentBid = 0;
			recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
			stockQuantity = 0;
		}

		/**
		 * copy c'tor for stock status
		 * 
		 * @param stockStatus
		 */
		public StockStatus(StockStatus stockStatus) {
			this();
			setSymbol(stockStatus.symbol);
			setCurrentAsk(stockStatus.currentAsk);
			setCurrentBid(stockStatus.currentBid);
			this.date = new Date(stockStatus.date.getTime());
			setRecommendation(stockStatus.recommendation);
			setStockQuantity(stockStatus.stockQuantity);
		}
		/**
		 * copy c'tor for filling stock status fields according to stock values
		 * 
		 * @param stock
		 */
		public StockStatus (Stock stock)
		{
			this();
			setSymbol(stock.getSymbol());
			setCurrentAsk(stock.getAsk());
			setCurrentBid(stock.getBid());
			setDate(new Date(stock.date.getTime()));
			setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
			setStockQuantity(0);
		}

		public String getSymbol() {
			return symbol;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public float getCurrentBid() {
			return currentBid;
		}

		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}

		public float getCurrentAsk() {
			return currentAsk;
		}

		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
			this.recommendation = recommendation;
		}

		public int getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
	}
}