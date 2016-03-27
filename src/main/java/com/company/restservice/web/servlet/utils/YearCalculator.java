package com.company.restservice.web.servlet.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Documentation for this class will be done later!
 * 
 * @author Simon Njenga
 * @since 0.1
 */
public class YearCalculator extends HttpServlet {

	/**
     * Serialization marker.
     */
	private static final long serialVersionUID = 5473151833849926992L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		Calendar cal = Calendar.getInstance();
		PrintWriter out = res.getWriter();
		out.print(cal.get(Calendar.YEAR));
	}
}
