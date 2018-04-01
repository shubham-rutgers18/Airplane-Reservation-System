package com.flywithme.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flywithme.app.AppConfig;

/**
 * Servlet implementation class AccountInformation
 */
public class AccountInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;

	private String phone;
	private String address;
	private String zipcode;
	private String creditcard;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String email = (String) request.getSession().getAttribute("email");
		int userId = 0;
		ResultSet rs, rs1;
		try {
			rs = AppConfig.getStatement().executeQuery("select u.first_name,u.last_name,u.password,u.role,u.user_id "
					+ "from user u where  u.email='" + email + "'");

			while (rs.next()) {
				firstName = rs.getString("first_name");
				lastName = rs.getString("last_name");
				userId = Integer.parseInt(rs.getString("user_id"));

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			rs1 = AppConfig.getStatement().executeQuery(
					"select c.address,c.creditcard,c.zipcode,c.contact_no from customer c where c.user_id='" + userId
							+ "'");

			while (rs1.next()) {
				address = rs1.getString("address");
				creditcard = rs1.getString("creditcard");
				zipcode = rs1.getString("zipcode");
				phone = rs1.getString("contact_no");

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println("<p>First Name: " + firstName);
		out.println("</p><p>Last Name: " + lastName);
		out.println("</p><p>Email: " + email);
		out.println("</p><p>Address: " + address);
		out.println("</p><p>Creditcard: " + creditcard);
		out.println("</p><p>Zipcode: " + zipcode);
		out.println("</p><p>Phone: " + phone + "</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
