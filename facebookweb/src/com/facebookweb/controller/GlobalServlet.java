package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebookweb.entity.FacebookEmployee;
import com.facebookweb.service.FacebookService;
import com.facebookweb.service.FacebookServiceInterface;

/**
 * Servlet implementation class GlobalServlet
 */
public class GlobalServlet extends HttpServlet {

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");

		String option = request.getParameter("ac");

		if (option.equals("register")) {
			String name = request.getParameter("name");
			String pass = request.getParameter("pass");
			String email = request.getParameter("email");
			String address = request.getParameter("address");

			FacebookEmployee fe = new FacebookEmployee();
			fe.setName(name);
			fe.setAddress(address);
			fe.setEmail(email);
			fe.setPass(pass);

			FacebookServiceInterface fs = FacebookService
					.createServiceObject("f");
			int i = fs.createProfileService(fe);

			if (i > 0) {
				out.println("profile created");
			} else {
				out.println("could not create profile");
			}
		}
		
		if (option.equals("login")) {
			
		}

		out.println("</body></html>");

	}

}
