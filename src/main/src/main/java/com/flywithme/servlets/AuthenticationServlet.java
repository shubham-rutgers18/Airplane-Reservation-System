package com.flywithme.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flywithme.app.AppConfig;

public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String storedPassword;
	private String firstName;
	private String lastName;
	private String role;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		RequestDispatcher rd = null;

		ResultSet rs;
		try {
			rs = AppConfig.getStatement().executeQuery("select u.first_name,u.last_name,u.password,u.role "
					+ "from user u, customer c where u.user_id=c.user_id and u.email='" + email + "'");

			while (rs.next()) {
				firstName = rs.getString("first_name");
				lastName = rs.getString("last_name");
				storedPassword = rs.getString("password");
				role = rs.getString("role");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (AppConfig.checkPassword(password, storedPassword)) {
			HttpSession session = request.getSession();
			session.setAttribute("firstName", this.firstName);
			session.setAttribute("lastName", lastName);
			session.setAttribute("email", email);
			if (role.equals("ADMIN")) {
				rd = request.getRequestDispatcher("admin.jsp");
			} else if (role.equals("USER")) {
				rd = request.getRequestDispatcher("user.jsp");
			}

			rd.forward(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").include(request, response);
		}

	}

}
