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

public class DeleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String deleteCustQuery = "delete from customer where user_id=?";
	private String deleteUserQuery = "delete from user where user_id=?";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("deleteEmailid");

		Statement st = AppConfig.getStatement();
		int userId = 0;
		String firstName = null;
		try {
			ResultSet rs = st.executeQuery("select first_name,user_id from user where email='" + email + "'");

			while (rs.next()) {
				userId = Integer.parseInt(rs.getString("user_id"));
				firstName = rs.getString("first_name");
			}
			rs.close();
			st.close();

			PreparedStatement ps = AppConfig.getPreparedStatement(deleteCustQuery);
			ps.setInt(1, userId);
			ps.executeUpdate();

			ps = AppConfig.getPreparedStatement(deleteUserQuery);
			ps.setInt(1, userId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/admin.jsp");
	}

}
