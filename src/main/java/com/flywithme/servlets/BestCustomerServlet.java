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

public class BestCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String bestCustomerQuery = "select first_name,last_name from user where user_id=(select t1.user_id from "
			+ "(select c.user_id,sum(r.booking_fee) as spends from reservations r "
			+ "join customerreservations c on r.reservation_id=c.reservation_id group by c.user_id)t1 where t1.spends=(select max(t2.spend) "
			+ "from (select c.user_id, sum(r.booking_fee) as spend from reservations r "
			+ "join customerreservations c on r.reservation_id=c.reservation_id group by c.user_id)t2));";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Statement st = AppConfig.getStatement();
		String firstName = null, lastName = null;
		try {
			ResultSet rs = st.executeQuery(bestCustomerQuery);

			while (rs.next()) {
				firstName = rs.getString("first_name");
				lastName = rs.getString("last_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String result = "The Best Customer is " + firstName + " " + lastName;
		request.getSession().setAttribute("result", result);
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}
