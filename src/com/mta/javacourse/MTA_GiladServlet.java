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
			int result = (num1+num2)*num3;
		
		String resStr = new String ("<h1>Result of --> " + "(" + num1 + "+" + num2 + ")" + "*" + num3 + " = " + result + "</h1>" );
		
		resp.setContentType("text/html");
		resp.getWriter().println(resStr);
	}
}
