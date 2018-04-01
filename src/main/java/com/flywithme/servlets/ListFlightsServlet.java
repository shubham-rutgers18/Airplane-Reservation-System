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

public class ListFlightsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String query = "select a.airline_name, fl.flight_id, fl.flightleg_id, a1.airport_name as origin, "
			+ "fl.scheduled_departure, a2.airport_name as dest, fl.scheduled_arrival,f.no_of_seats,"
			+ "f.fare,f.working_day from flightleg fl join flight f on "
			+ "fl.airline_id=f.airline_id and fl.flight_id=f.flight_id join airline a "
			+ "on a.airline_id=fl.airline_id join airport a1 on a1.airport_id=fl.origin_airport_id "
			+ "join airport a2 on a2.airport_id=fl.destination_airport_id";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		out.println("<table class=\"striped\"><thead><tr><td>Airline</td><td>Flight</td><td>FlightLeg</td>"
				+ "<td>Origin</td><td>Dep. time</td><td>Destination</td><td>Arrival time</td><td>No of seats</td>"
				+ "<td>Fare</td><td>Working Day</td></tr></thead><tbody>");
		ResultSet rs = null;
		try {

			rs = st.executeQuery(query);
			while (rs.next()) {
				String airline = (rs.getString("airline_name"));
				String flight = (rs.getString("flight_id"));
				String fl = (rs.getString("flightleg_id"));
				String a1 = (rs.getString("origin"));
				String dt = (rs.getString("scheduled_departure"));
				String a2 = (rs.getString("dest"));
				String at = (rs.getString("scheduled_arrival"));
				String ns = rs.getString("no_of_seats");
				String fare = rs.getString("fare");
				String wd = rs.getString("working_day");

				StringBuffer s = new StringBuffer();
				if (wd != null) {
					char[] work = wd.toCharArray();
					s=(work[0]=='1')?s.append("SU,"):s.append("");
					s=(work[1]=='1')?s.append("MO,"):s.append("");
					s=(work[2]=='1')?s.append("TU,"):s.append("");
					s=(work[3]=='1')?s.append("WE,"):s.append("");
					s=(work[4]=='1')?s.append("TH,"):s.append("");
					s=(work[5]=='1')?s.append("FR,"):s.append("");
					s=(work[6]=='1')?s.append("SA"):s.append("");
				}

				out.println("<tr><td>" + airline + "</td><td>" + flight + "</td><td>" + fl + "</td><td>" + a1
						+ "</td><td>" + dt + "</td><td>" + a2 + "</td>" + "<td>" + at + "</td><td>" + ns + "</td><td>"
						+ fare + "</td><td>" + s.toString() + "</td></tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</tbody></table>");
		out.println("</body>");
		out.println("</html>");

	}

}
