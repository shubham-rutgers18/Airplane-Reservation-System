package com.flywithme.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flywithme.app.AppConfig;

public class EditSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String updateUserDetails = "update user set first_name=?,last_name=? where user_id=?";
	private String updateCustDetails = "update customer set address=?,zipcode=?,contact_no=?,creditcard=? where user_id=?";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName1");
		String lastName = request.getParameter("lastName1");
		String address = request.getParameter("address1");
		int zipcode = (int) Integer.parseInt(request.getParameter("zipcode1"));
		long contactNo = Long.parseLong(request.getParameter("contactNo1"));
		long creditCardNo = Long.parseLong(request.getParameter("creditCardNo1"));
		String email = (String) request.getSession().getAttribute("editEmail");

		Statement st = AppConfig.getStatement();
		PreparedStatement ps = AppConfig.getPreparedStatement(updateUserDetails);
		int userId = 0;

		ResultSet rs;
		try {
			rs = st.executeQuery("select user_id from user where email='" + email + "'");
			while (rs.next()) {
				userId = Integer.parseInt(rs.getString("user_id"));
			}

			rs.close();

			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setInt(3, userId);
			ps.executeUpdate();
			ps.close();

			ps = AppConfig.getPreparedStatement(updateCustDetails);
			ps.setString(1, address);
			ps.setInt(2, zipcode);
			ps.setLong(3, contactNo);
			ps.setLong(4, creditCardNo);
			ps.setInt(5, userId);
			ps.executeUpdate();
			ps.close();

			PrintWriter out = response.getWriter();
			out.println("Details of User " + firstName + " updated");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/admin.jsp");
	}

}
