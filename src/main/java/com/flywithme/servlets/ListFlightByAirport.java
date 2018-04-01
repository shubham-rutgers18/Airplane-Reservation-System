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
 * Servlet implementation class ListFlightByAirport
 */
public class ListFlightByAirport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String arrival = "select aa.airline_name, f.flight_id, f.flightleg_id, f.scheduled_departure, a2.airport_name as going_to "
			+ "from flightleg f join airline aa on aa.airline_id=f.airline_id " + "join airport a1 on a1.airport_id=f.origin_airport_id "
			+ "join airport a2 on a2.airport_id=f.destination_airport_id " + "where a1.airport_id ='";

	private String departure = "select aa.airline_name, f.flight_id, f.flightleg_id, f.scheduled_arrival, a1.airport_name as arriving_from "
			+ "from flightleg f join airline aa on aa.airline_id=f.airline_id " + "join airport a1 on a1.airport_id=f.origin_airport_id "
			+ "join airport a2 on a2.airport_id=f.destination_airport_id " + "where a2.airport_id ='";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Statement st = AppConfig.getStatement();

		String airportId = request.getParameter("flightAirportId");
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
		out.println("<h5>Flights going from the airport</h5>");
		out.println("<table class=\"striped\"><thead><tr><td>Airline</td><td>Flight</td><td>FlightLeg</td>"
				+ "<td>Departure Time</td><td>Heading to</td></tr></thead><tbody>");
		ResultSet rs = null;
		try {

			rs = st.executeQuery(arrival + airportId + "'");
			while (rs.next()) {
				String airline = (rs.getString("airline_name"));
				String flight = (rs.getString("flight_id"));
				String fl = (rs.getString("flightleg_id"));
				String dt = (rs.getString("scheduled_departure"));
				String a2 = (rs.getString("going_to"));

				out.println("<tr><td>" + airline + "</td><td>" + flight + "</td><td>" + fl + "</td><td>" + dt + "</td><td>" + a2 + "</td></tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</tbody></table><br><br>");
		out.println("<h5>Flights coming into the airport</h5>");

		out.println("<table><thead><tr><td>Airline</td><td>Flight</td><td>FlightLeg</td>"
				+ "<td>Departure Time</td><td>Arrival From</td></tr></thead><tbody>");

		rs = null;
		try {

			rs = st.executeQuery(departure + airportId + "'");
			while (rs.next()) {
				String airline = (rs.getString("airline_name"));
				String flight = (rs.getString("flight_id"));
				String fl = (rs.getString("flightleg_id"));
				String dt = (rs.getString("scheduled_arrival"));
				String a2 = (rs.getString("arriving_from"));

				out.println("<tr><td>" + airline + "</td><td>" + flight + "</td><td>" + fl + "</td><td>" + dt + "</td><td>" + a2 + "</td></tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</tbody></table>");
		out.println("</body>");
		out.println("</html>");

	}

}
