package com.mta.javacourse;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StockDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Stock s1 = new Stock();
		Stock s2 = new Stock();
		Stock s3 = new Stock();
		
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

		resp.setContentType("text/html");

		resp.getWriter().println(s1.getHtmlDescription() + "<br>");
		resp.getWriter().println(s2.getHtmlDescription() + "<br>");
		resp.getWriter().println(s3.getHtmlDescription() + "<br>");

	}
}