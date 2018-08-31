package com.flywithme.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flywithme.app.AppConfig;

public class AddPassengerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String passList = "insert into passengerlist(reservation_id,airline_id,flight_id,flightleg_id,name) values (?,?,?,?,?)";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String bookingType = (String) session.getAttribute("bkType");
		int number = (Integer) session.getAttribute("nos");
		PreparedStatement ps = AppConfig.getPreparedStatement(passList);

		List<String> fn = new ArrayList<String>();

		if (number == 1) {
			fn.add(request.getParameter("fullname1"));
		}
		if (number == 2) {
			fn.add(request.getParameter("fullname1"));
			fn.add(request.getParameter("fullname2"));
		}
		if (number == 3) {
			fn.add(request.getParameter("fullname1"));
			fn.add(request.getParameter("fullname2"));
			fn.add(request.getParameter("fullname3"));
		}

		try {
			if (bookingType.equals("oneway")) {
				int flightId = (Integer) session.getAttribute("flightId");
				int flightlegId = (Integer) session.getAttribute("flightlegId");
				String airlineId = (String) session.getAttribute("airlineId");
				int reId = (Integer) session.getAttribute("reId");

				for (String name : fn) {
					ps.setInt(1, reId);
					ps.setString(2, airlineId);
					ps.setInt(3, flightId);
					ps.setInt(4, flightlegId);
					ps.setString(5, name);
					ps.executeUpdate();
				}

			} else if (bookingType.equals("twoway")) {

				int flight1Id = (Integer) session.getAttribute("flight1Id");
				int flightleg1Id = (Integer) session.getAttribute("flightleg1Id");
				String airline1Id = (String) session.getAttribute("airline1Id");
				int flight2Id = (Integer) session.getAttribute("flight2Id");
				int flightleg2Id = (Integer) session.getAttribute("flightleg2Id");
				String airline2Id = (String) session.getAttribute("airline2Id");
				int reId = (Integer) session.getAttribute("reId");

				for (String name : fn) {
					ps.setInt(1, reId);
					ps.setString(2, airline1Id);
					ps.setInt(3, flight1Id);
					ps.setInt(4, flightleg1Id);
					ps.setString(5, name);
					ps.executeUpdate();
				}

				for (String name : fn) {
					ps.setInt(1, reId);
					ps.setString(2, airline2Id);
					ps.setInt(3, flight2Id);
					ps.setInt(4, flightleg2Id);
					ps.setString(5, name);
					ps.executeUpdate();
				}

			}
		} catch (SQLException e) {

		}
		response.sendRedirect("user.jsp");
	}

}
