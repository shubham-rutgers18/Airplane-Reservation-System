package com.flywithme.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
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
import javax.servlet.http.HttpSession;

import com.flywithme.app.AppConfig;

public class BookOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String aa1Id;
	private int ff1Id;
	private String aa2Id;
	private int leg2Flight;
	private int fId;
	private int f2Id;
	private int f1Id;
	private int flight2Id;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String value = request.getParameter("book");

		String[] c = value.split(",");

		String getRId = "select max(reservation_id) as max from reservations";
		String addReserve = "insert into reservations(airline_id,reservation_id, flight_id,flightleg_id,reservation_date,booking_fee,seats_reserved,bookingdate) values(?,?,?,?,?,?,?,?)";
		String custReserve = "insert into customerreservations(user_id,reservation_id) values(?,?)";
		String updateStatus = "update flightstatus set no_of_seats=no_of_seats-? where airline_id=? and flight_id=? and flightleg_id=? and journey_date=? and no_of_seats>0";

		String aId, departDate, fare1;
		float fare;
		int flightId, nos, uId = 0;
		int flightlegId;
		Date reserveDate = null;
		Statement st = AppConfig.getStatement();
		PreparedStatement ps = AppConfig.getPreparedStatement(addReserve);

		HttpSession session = request.getSession();

		if (c[0].equals("1")) {
			// "1," + aId + ","
			// + flightId + "," + flightlegId + "," + fare + "," + departDate + ","
			aId = c[1];
			flightId = Integer.parseInt(c[2]);
			flightlegId = Integer.parseInt(c[3]);
			fare = Float.parseFloat(c[4]);

			int reId = 0;
			SimpleDateFormat availDate = new SimpleDateFormat("yyyy-MM-dd");

			try {
				reserveDate = availDate.parse(c[5]);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			nos = Integer.parseInt(c[6]);

			try {
				ResultSet rs = st.executeQuery(getRId);
				while (rs.next()) {
					reId = Integer.parseInt(rs.getString("max")) + 1;
				}
				rs.close();

				ps.setString(1, aId);
				ps.setInt(2, reId);
				ps.setInt(3, flightId);
				ps.setInt(4, flightlegId);
				ps.setDate(5, new java.sql.Date(reserveDate.getTime()));
				ps.setFloat(6, fare * nos);
				ps.setInt(7, nos);
				ps.setDate(8, new java.sql.Date(new Date().getTime()));

				ps.executeUpdate();

				rs = AppConfig.getStatement().executeQuery("select u.user_id from user u where u.email='"
						+ request.getSession().getAttribute("email") + "'");

				while (rs.next()) {
					uId = Integer.parseInt(rs.getString("user_id"));
				}

				ps = AppConfig.getPreparedStatement(custReserve);
				ps.setInt(1, uId);
				ps.setInt(2, reId);

				ps.executeUpdate();

				ps = AppConfig.getPreparedStatement(updateStatus);
				ps.setInt(1, nos);
				ps.setString(2, aId);
				ps.setInt(3, flightId);
				ps.setInt(4, flightlegId);
				ps.setDate(5, new java.sql.Date(reserveDate.getTime()));

				ps.executeUpdate();

				session.setAttribute("bkType", "oneway");
				session.setAttribute("flightId", flightId);
				session.setAttribute("flightlegId", flightlegId);
				session.setAttribute("airlineId", aId);
				session.setAttribute("reId", reId);
				session.setAttribute("nos", nos);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (c[0].equals("2")) {
			/*
			 * "2," + aa1Id + "," + ff1Id + ","+f1Id+"," + aa2Id + "," + leg2Flight + "," +
			 * f2Id + "," + departDate + "," + fare1 + "," + fare2 + "," + nos + "
			 */
			aa1Id = c[1];
			ff1Id = Integer.parseInt(c[2]);
			f1Id = Integer.parseInt(c[3]);
			aa2Id = c[4];
			flight2Id = Integer.parseInt(c[5]);
			f2Id = Integer.parseInt(c[6]);

			int reId = 0;
			SimpleDateFormat availDate = new SimpleDateFormat("yyyy-MM-dd");

			try {
				reserveDate = availDate.parse(c[7]);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			float far1 = Float.parseFloat(c[8]);
			float fare2 = Float.parseFloat(c[9]);

			nos = Integer.parseInt(c[10]);

			try {
				ResultSet rs = st.executeQuery(getRId);
				while (rs.next()) {
					reId = Integer.parseInt(rs.getString("max")) + 1;
				}
				rs.close();

				ps.setString(1, aa1Id);
				ps.setInt(2, reId);
				ps.setInt(3, ff1Id);
				ps.setInt(4, f1Id);
				ps.setDate(5, new java.sql.Date(reserveDate.getTime()));
				ps.setFloat(6, far1 * nos);
				ps.setInt(7, nos);
				ps.setDate(8, new java.sql.Date(new Date().getTime()));

				ps.executeUpdate();

				ps.setString(1, aa2Id);
				ps.setInt(2, reId);
				ps.setInt(3, flight2Id);
				ps.setInt(4, f2Id);
				ps.setDate(5, new java.sql.Date(reserveDate.getTime()));
				ps.setFloat(6, fare2 * nos);
				ps.setInt(7, nos);
				ps.setDate(8, new java.sql.Date(new Date().getTime()));

				ps.executeUpdate();

				rs = AppConfig.getStatement().executeQuery("select u.user_id from user u where u.email='"
						+ request.getSession().getAttribute("email") + "'");

				while (rs.next()) {
					uId = Integer.parseInt(rs.getString("user_id"));
				}

				ps = AppConfig.getPreparedStatement(custReserve);
				ps.setInt(1, uId);
				ps.setInt(2, reId);

				ps.executeUpdate();

				ps = AppConfig.getPreparedStatement(updateStatus);
				ps.setInt(1, nos);
				ps.setString(2, aa1Id);
				ps.setInt(3, ff1Id);
				ps.setInt(4, f1Id);
				ps.setDate(5, new java.sql.Date(reserveDate.getTime()));

				ps.executeUpdate();

				ps = AppConfig.getPreparedStatement(updateStatus);
				ps.setInt(1, nos);
				ps.setString(2, aa2Id);
				ps.setInt(3, flight2Id);
				ps.setInt(4, f2Id);
				ps.setDate(5, new java.sql.Date(reserveDate.getTime()));

				ps.executeUpdate();

				session.setAttribute("bkType", "twoway");
				session.setAttribute("flight1Id", ff1Id);
				session.setAttribute("flightleg1Id", f1Id);
				session.setAttribute("airline1Id", aa1Id);
				session.setAttribute("flight2Id", flight2Id);
				session.setAttribute("flightleg2Id", f2Id);
				session.setAttribute("airline2Id", aa2Id);
				session.setAttribute("reId", reId);
				session.setAttribute("nos", nos);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		request.getRequestDispatcher("addPassenger.jsp").forward(request, response);
	}

}
