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

public class FlightStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String sql = "select aa.airline_name, fl.flight_id,fl.flightleg_id,f.journey_date,f.status "
			+ "from flightleg fl " + "join airline aa on aa.airline_id=fl.airline_id "
			+ "join flightstatus f on f.airline_id=fl.airline_id and f.flight_id=fl.flight_id and f.flightleg_id=fl.flightleg_id";

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
				+ "<td>Journey Date</td><td>Status</td></tr></thead><tbody>");

		ResultSet rs = null;
		try {

			rs = st.executeQuery(sql);

			while (rs.next()) {
				String airline = (rs.getString("airline_name"));
				String flight = (rs.getString("flight_id"));
				String fl = (rs.getString("flightleg_id"));
				String a1 = (rs.getString("journey_date"));
				String dt = (rs.getString("status"));

				out.println("<tr><td>" + airline + "</td><td>" + flight + "</td><td>" + fl + "</td><td>" + a1
						+ "</td><td>" + dt + "</td></tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</tbody></table>");
		out.println("</body>");
		out.println("</html>");
	}

}
