package com.flywithme.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flywithme.app.AppConfig;

public class ReservationByUIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String query = "select a.airline_name,r.flight_id,r.flightleg_id,a1.airport_name as "
			+ "origin,f.scheduled_departure,a2.airport_name as dest,f.scheduled_arrival,r.reservation_date "
			+ "from customerreservations c join user u on u.user_id=c.user_id "
			+ "join reservations r on r.reservation_id=c.reservation_id "
			+ "join flightleg f on f.airline_id=r.airline_id and f.flight_id=r.flight_id and f.flightleg_id=r.flightleg_id "
			+ "join airline a on a.airline_id=r.airline_id " + "join airport a1 on a1.airport_id=f.origin_airport_id "
			+ "join airport a2 on a2.airport_id=f.destination_airport_id " + "where u.email='";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("reserveEmail");
		Statement st = AppConfig.getStatement();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><meta name=\"viewport\" content=\"width = device-width, initial-scale = 1\">\r\n" + 
				"<link rel=\"stylesheet\"\r\n" + 
				"	href=\"https://fonts.googleapis.com/icon?family=Material+Icons\">\r\n" + 
				"<link rel=\"stylesheet\"\r\n" + 
				"	href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css\">\r\n" + 
				"<script type=\"text/javascript\"\r\n" + 
				"	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" + 
				"<script type=\"text/javascript\"\r\n" + 
				"	src=\" https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js\"></script></head>");
		out.println("<body>");
		out.println("<table><thead><tr><td>Airline</td><td>Flight</td><td>FlightLeg</td>"
				+ "<td>Origin</td><td>Dep. time</td><td>Destination</td><td>Arrival time</td><td>Reservation Time</td></tr></thead><tbody>");
		ResultSet rs = null;
		try {
			String sql = query + email + "'";
			System.out.println(sql);
			rs = st.executeQuery(query + email + "'");
			while (rs.next()) {
				String airline = (rs.getString("airline_name"));
				String flight = (rs.getString("flight_id"));
				String fl = (rs.getString("flightleg_id"));
				String a1 = (rs.getString("origin"));
				String dt = (rs.getString("scheduled_departure"));
				String a2 = (rs.getString("dest"));
				String at = (rs.getString("scheduled_arrival"));
				String rd = rs.getString("reservation_date");
				out.println(
						"<tr><td>" + airline + "</td><td>" + flight + "</td><td>" + fl + "</td><td>" + a1 + "</td><td>"
								+ dt + "</td><td>" + a2 + "</td>" + "<td>" + at + "</td><td>" + rd + "</td></tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</tbody></table>");
		out.println("</body>");
		out.println("</html>");
	}

}
