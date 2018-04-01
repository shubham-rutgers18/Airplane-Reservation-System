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

public class RevenueByFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String airlineId = request.getParameter("revenueByAirlineId");
		String flightId = request.getParameter("revenueByFlightId");
		String sum = null;
		Statement st = AppConfig.getStatement();

		try {
			String sql = "select sum(r.booking_fee) as sum from reservations r where r.airline_id='" + airlineId
					+ "' and r.flight_id='" + flightId + "' group by r.airline_id,r.flight_id";

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				sum = rs.getString("sum");
			}
			request.getSession().setAttribute("result", "The revenue for Flight " + airlineId + flightId + " = " + sum);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}
