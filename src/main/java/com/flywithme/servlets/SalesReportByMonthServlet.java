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

public class SalesReportByMonthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String month = request.getParameter("month");
		String year = request.getParameter("year");

		String getTotal = "select sum(r.booking_fee) as sum from reservations r WHERE MONTH(bookingdate) ='" + month
				+ "' AND YEAR(bookingdate)='" + year + "'";

		String salesReport = "select r.airline_id,r.flight_id, count(*) as no_of_reservations, sum(r.booking_fee) as revenue "
				+ "from reservations r " + "WHERE MONTH(bookingdate) ='" + month + "' AND YEAR(bookingdate) ='" + year
				+ "' " + "group by r.airline_id,r.flight_id";

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
		out.println("<center><h5>Sales Report for the month " + month + " </h5><br></center>");
		out.println("<table class=\"striped\"><thead><tr><td>Airline</td><td>Flight ID</td><td>No of seats booked</td>"
				+ "<td>Revenue</td></tr></thead><tbody>");

		Statement st = AppConfig.getStatement();
		try {
			ResultSet rs = st.executeQuery(salesReport);
			while (rs.next()) {
				String airId = rs.getString("airline_id");
				String fId = rs.getString("flight_id");
				String nos = rs.getString("no_of_reservations");
				String revenue = rs.getString("revenue");

				out.println("<tr><td>" + airId + "</td><td>" + fId + "</td><td>" + nos + "</td><td>" + revenue
						+ "</td></tr>");
			}
			out.println("</tbody></table><br><br>");
			rs.close();
			rs = st.executeQuery(getTotal);
			while (rs.next()) {
				String sum = rs.getString("sum");
				out.println("<br><br>The total revenue generated is " + sum);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</body>");
		out.println("</html>");
	}

}
