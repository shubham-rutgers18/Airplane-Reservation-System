package com.flywithme.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flywithme.app.AppConfig;

public class RevenueByCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String userRevenue = "select sum(revenue) as sum from "
			+ "(select r.booking_fee as revenue from reservations join customer reservations"
			+ " c on c.reservation_id=reservation_id join user u on u.user_id= c.user_id where u.email=?)";

	private String userDetails = "select first_name,last_name from user where email='";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("revenueCustId");
		String revenue = null, fn = null, ln = null;
		PreparedStatement ps = AppConfig.getPreparedStatement(userRevenue);
		Statement st = AppConfig.getStatement();
		ResultSet rs;
		try {
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				revenue = rs.getString("sum");
			}

			rs = st.executeQuery(userDetails + email + "'");
			while (rs.next()) {
				fn = rs.getString("first_name");
				ln = rs.getString("last_name");
			}
		} catch (SQLException e) {
			// todo
		}
		String result = "The Revenue for user " + fn + " " + ln + " is " + revenue;
		request.getSession().setAttribute("result", result);

		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}
