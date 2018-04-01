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

/**
 * Servlet implementation class ReservationByFlightServlet
 */
public class ReservationByFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String flightId = request.getParameter("reservelistFlight");
		String airlineId = request.getParameter("reservelistAirline");
		String fromDate = request.getParameter("fromReserveDate");
		String toDate = request.getParameter("toReserveDate");

		Statement st = AppConfig.getStatement();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><meta name=\"viewport\" content=\"width = device-width, initial-scale = 1\">\r\n"
				+ "<link rel=\"stylesheet\"\r\n"
				+ "	href=\"https://fonts.googleapis.com/icon?family=Material+Icons\">\r\n"
				+ "<link rel=\"stylesheet\"\r\n"
				+ "	href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css\">\r\n"
				+ "<script type=\"text/javascript\"\r\n"
				+ "	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
				+ "<script type=\"text/javascript\"\r\n"
				+ "	src=\" https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js\"></script></head>");
		out.println("<body>");
		out.println("<h5>Reservation in Flight " + airlineId + flightId + "</h5><br>");

		try {
			ResultSet rs = st.executeQuery(
					"select r.reservation_id,u.first_name,u.last_name,a1.airport_name as origin, a2.airport_name as dest,r.seats_reserved"
							+ ",r.booking_fee as fare from reservations r "
							+ "join flightleg f on f.airline_id=r.airline_id and f.flight_id=r.flight_id and f.flightleg_id=r.flightleg_id "
							+ "join airport a1 on a1.airport_id=f.origin_airport_id "
							+ "join airport a2 on a2.airport_id=f.destination_airport_id "
							+ "join customerreservations c on c.reservation_id=r.reservation_id "
							+ "join user u on u.user_id=c.user_id " + "where r.airline_id='" + airlineId
							+ "' and r.flight_id='" + flightId + "' and r.reservation_date between '" + fromDate
							+ "' AND '" + toDate + "'");

			out.println("<table class=\"striped\"><thead><tr><td>Reservation ID</td><td>First Name</td>"
					+ "<td>Last Name</td><td>Source Airport</td><td>Destination Airport</td>"
					+ "<td>No of seats reserved</td><td>Fare</td></tr></thead><tbody>");

			while (rs.next()) {
				String rId = rs.getString("reservation_id");
				String fn = rs.getString("first_name");
				String ln = rs.getString("last_name");
				String origin = rs.getString("origin");
				String dest = rs.getString("dest");
				String seats = rs.getString("seats_reserved");
				String fare = rs.getString("fare");
				out.println("<tr><td>" + rId + "</td><td>" + fn + "</td><td>" + ln + "</td><td>" + origin + "</td>"
						+ "<td>" + dest + "</td><td>" + seats + "</td><td>" + fare + "</td></tr>");
			}
			rs.close();
			st.close();
			out.println("</tbody></table><br><br>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</body>");
		out.println("</html>");
	}

}
