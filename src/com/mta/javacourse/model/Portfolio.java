package com.mta.javacourse.model;

import java.util.ArrayList;
import java.util.List;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.IllegalQuantityException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistsException;

/**
 * Class for creating a stock portfolio
 * 
 * @author Gilad David
 *
 */
public class Portfolio{

	public final static int MAX_PORTFOLIO_SIZE = 5;
	public enum ALGO_RECOMMENDATION {DO_NOTHING, BUY, SELL};
	private String title;
	private float balance;
	private List <StockStatus> stockStatus;

	/**
	 * c'tor for initializing portfolio members
	 */

	public Portfolio() {
		stockStatus = new ArrayList<StockStatus>(MAX_PORTFOLIO_SIZE);
	}

/**
 * c'tor for adding stocks to array list
 * @param sts
 */
	public Portfolio (List<StockStatus> sts)
	{
		this ();
		for (int i = 0; i < sts.size(); i++)
		{
			stockStatus.add(sts.get(i));
		}
	}
	/**
	 * copy c'tor for making instance copies of Portfolio object
	 * 
	 * @param portfolio
	 */
	public Portfolio(Portfolio portfolio) {
		this();
		this.setTitle(portfolio.getTitle());
		this.setBalance(portfolio.getBalance());

		stockStatus.addAll(portfolio.stockStatus);
	}

	public StockStatus[] getStocks() {
		StockStatus[] res = new StockStatus[stockStatus.size()];
		res =  stockStatus.toArray(res);
		return res;
	}

	/**
	 * function for adding new stock to portfolio within portfolio size limits
	 * 
	 * @param stock
	 * @throws StockAlreadyExistsException 
	 * @throws PortfolioFullException 
	 */
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException {

		if(stockStatus.size() == MAX_PORTFOLIO_SIZE)
		{
			throw new PortfolioFullException(stockStatus.size());
		}

		for (int i = 0; i < stockStatus.size(); i++) {

			if (stockStatus.get(i).getSymbol().equals(stock.getSymbol())) 
			{
				throw new StockAlreadyExistsException(stock.getSymbol());
			}
		}

		stockStatus.add (new StockStatus(stock));	
	}

	/**
	 * function for removing a specific stock from portfolio (according to its symbol)
	 * 
	 * @param symbol
	 * @throws StockNotExistsException 
	 */
	public void removeStock(String symbol) throws StockNotExistsException, IllegalQuantityException
	{	
		int index = getSymbolIndex(symbol);

		if(index ==-1) {
			throw new StockNotExistsException(symbol);
		}

		sellStock(symbol, -1);

		while (index < stockStatus.size())
		{
			stockStatus.remove (index);
			index++;
		}
	}
	/**
	 * function for selling stocks and updating portfolio balance
	 * 
	 * @param symbol
	 * @param quantity
	 * @return
	 * @throws StockNotExistsException 
	 * @throws IllegalQuantityException 
	 */
	public void sellStock (String symbol, int quantity) throws StockNotExistsException, IllegalQuantityException
	{
		int index = getSymbolIndex(symbol);

		if (index == -1)
		{
			throw new StockNotExistsException(symbol);
		}

		if(quantity < -1)
		{
			throw new IllegalQuantityException("Can't sell negative amount of stocks");
		}

		if(quantity > stockStatus.get(index).getStockQuantity())
		{
			throw new IllegalQuantityException("Not enough stocks to sell");
		}

		if(quantity == -1)
		{	
			quantity = stockStatus.get(index).getStockQuantity();
		}

		updateBalance(quantity * stockStatus.get(index).getBid());
		stockStatus.get(index).setStockQuantity(stockStatus.get(index).getStockQuantity() - quantity);
	}
	/**
	 * function for buying stocks and updating portfolio balance
	 * 
	 * @param symbol
	 * @param quantity
	 * @return
	 * @throws BalanceException 
	 * @throws StockNotExistsException 
	 * @throws IllegalQuantityException 
	 */
	public void buyStock(String symbol, int quantity) throws BalanceException, StockNotExistsException, IllegalQuantityException 
	{
		int index = getSymbolIndex(symbol);

		if (index == -1)
		{
			throw new StockNotExistsException(symbol);
		}

		if (quantity < -1)
		{
			throw new IllegalQuantityException();
		}

		if (getBalance() < 0)
		{
			throw new BalanceException(getBalance());
		}

		if(quantity == -1)
		{
			quantity = (int) (balance / stockStatus.get(index).getAsk());
		}

		float buyPrice = quantity * (stockStatus.get(index).getAsk()); 		

		if (buyPrice > balance)
		{
			throw new BalanceException(getBalance());
		}
		updateBalance(-buyPrice);
		stockStatus.get(index).setStockQuantity(stockStatus.get(index).getStockQuantity() + quantity);
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
		for (int i = 0; i < stockStatus.size(); i++)
			res+=stockStatus.get(i).getStockQuantity() * stockStatus.get(i).getBid();
		return res;
	}
/**
 * function for searching a stock in Portfolio according to its symbol
 * @param symbol
 * @return
 * @throws StockNotExistsException
 */
	public StockStatus findBySymbol(String symbol) throws StockNotExistsException {
		for(int i=0; i<stockStatus.size(); i++){
			if(stockStatus.get(i).getSymbol().toLowerCase().equals(symbol.toLowerCase()))
			{
				return stockStatus.get(i);
			}
		}

		throw new StockNotExistsException(symbol);
	} 
/***
 * function for retrieving stock index by symbol 
 * @param symbol
 * @return
 */
	private int getSymbolIndex(String symbol)
	{
		for(int i = 0; i < stockStatus.size(); i++)
		{
			if(stockStatus.get(i).getSymbol().toLowerCase().equals(symbol.toLowerCase()))
			{
				return i;
			}
		}
		return -1;
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


	// Setters & Getters-->

	public List<StockStatus> getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(List<StockStatus> stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getBalance()
	{
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
}