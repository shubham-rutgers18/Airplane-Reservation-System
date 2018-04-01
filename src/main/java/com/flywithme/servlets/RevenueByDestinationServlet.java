package com.flywithme.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flywithme.app.AppConfig;

public class RevenueByDestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String city = request.getParameter("city");

		String sql = "select sum(r.booking_fee) as revenue from reservations r "
				+ "join flightleg f on f.airline_id=r.airline_id and f.flight_id=r.flight_id and f.flightleg_id=r.flightleg_id "
				+ "join airport a2 on a2.airport_id=f.destination_airport_id " + "where a2.city='" + city + "'";

		Statement st = AppConfig.getStatement();

		String revenue = null;
		try {
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				revenue = rs.getString("revenue");
			}
			request.getSession().setAttribute("result", "The revenue for city "+city+" is "+revenue);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}
