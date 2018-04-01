package com.flywithme.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flywithme.app.AppConfig;

public class ReservedSeatsByCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int flightId = Integer.parseInt(request.getParameter("custflightid"));
		String custAirlineId = request.getParameter("custairlineid");
		String flightDateString = request.getParameter("custflightDate");
		Date flightDate = null;
		SimpleDateFormat availDate = new SimpleDateFormat("yyyy-MM-dd");

		try {
			flightDate = availDate.parse(flightDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

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
		out.println("<h5>List of customers with reserved seats on " + flightDate + " </h5><br>");
		out.println(
				"<table class=\"striped\"><thead><tr><td>FirstName</td><td>Last Name</td><td>No of seats reserved</td></tr></thead><tbody>");

		Statement ps = AppConfig.getStatement();
		try {
			String query = "select distinct u.first_name, u.last_name,r.seats_reserved from  user u "
					+ "join customerreservations c on c.user_id=u.user_id "
					+ "join reservations r on c.reservation_id=r.reservation_id " + "where r.airline_id='"
					+ custAirlineId + "' and r.flight_id=" + flightId + " and r.reservation_date='" + flightDateString
					+ "'";
			ResultSet rs = ps.executeQuery(query);
			while (rs.next()) {
				String fn = rs.getString("first_name");
				String ln = rs.getString("last_name");
				String nos = rs.getString("seats_reserved");
				out.println("<tr><td>" + fn + "</td><td>" + ln + "</td><td>" + nos + "</td></tr>");
			}
			rs.close();
			ps.close();
			out.println("</tbody></table><br><br>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</body>");
		out.println("</html>");
	}

}
