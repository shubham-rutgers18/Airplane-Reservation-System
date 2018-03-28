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
	private String password;
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
			rs = AppConfig.getConnectionStatement().executeQuery(
					"select u.first_name,u.last_name,u.password,u.role from user u, customer c where u.user_id=c.user_id and u.email="
							+ email);

			while (rs.next()) {
				firstName = rs.getString(0);
				lastName = rs.getString(1);
				request.setAttribute("firstName", firstName);
				request.setAttribute("lastName", lastName);
				this.password = rs.getString(2);
				role = rs.getString(3);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String encryptedPassword = AppConfig.passwordEncryptor().encryptPassword(password);

		if (AppConfig.checkPassword(encryptedPassword, this.password)) {
			HttpSession session = request.getSession();
			session.setAttribute("firstName", this.firstName);
			session.setAttribute("lastName", lastName);
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
