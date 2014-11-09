package com.mta.javacourse;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MTA_GiladServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello,My Name Is Gilad David");
	}
}
