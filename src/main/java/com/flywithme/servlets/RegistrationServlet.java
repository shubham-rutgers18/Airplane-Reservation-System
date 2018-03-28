package com.flywithme.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flywithme.app.AppConfig;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String zipcode = request.getParameter("zipcode");// int
		String contactNo = request.getParameter("contactNo");
		String creditCardNo = request.getParameter("creditCardNo");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Statement st = AppConfig.getConnectionStatement();
		try {
			st.executeQuery("insert into user values('" + email + "','" + firstName + "','" + lastName + "','"
					+ password + "','USER'");
			st.executeQuery("insert into customer values('" + "','" + address + "','" + contactNo + "','" + creditCardNo
					+ "','" + zipcode + "','");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
