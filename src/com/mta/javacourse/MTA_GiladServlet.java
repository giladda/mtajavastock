package com.mta.javacourse;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MTA_GiladServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		int num1 = 4;
		int num2 = 3;
		int num3 = 7;
		int result = (num1 + num2) * num3;

		String resStr1 = new String("<h1>Result of --> " + "(" + num1 + "+" + num2 + ")" + "*" + num3 + " = " + result + "</h1>");
		
		//calculate area of circle
		int radius = 50;
		double circleArea = (radius * radius) * Math.PI;
		String roundArea = String.format("%.2f", circleArea);
		String line1 = new String ("calculation 1: Area of circle with radius " + radius + " is: " + roundArea + " square-cm.");
		
		
		//calculate opposite of triangle
		int hypotenuse = 50;
		int angleB = 30;
		double angleBInRadians = Math.toRadians(angleB);
		double opposite = hypotenuse * angleBInRadians;
		String roundOpposite = String.format("%.2f", opposite);
		String line2 = new String ("calculation 2: Length of opposite, where angle B is: " + angleB + " is: " + roundOpposite + ".");
		
		//calculate power
		int base = 20;
		int exp = 13;
		long powerRes = (long) Math.pow(base, exp);
		String line3 = new String ("calculation 3: Power of " + base +" with exp of " + exp + " is: " + powerRes);
		
		String resultStr = resStr1 + "<br>" + line1 + "<br>" + line2 + "<br>" + line3;
		
		resp.setContentType("text/html");
		resp.getWriter().println(resultStr);
	}
}
