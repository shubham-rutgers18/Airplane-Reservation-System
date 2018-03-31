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

public class EditCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String customerDetails = "select first_name,last_name,address,zipcode,contact_no,creditcard from customer c, user u "
			+ "where c.user_id=u.user_id and u.email='";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("editemailid");

		Statement st = AppConfig.getStatement();
		try {
			ResultSet rs = st.executeQuery(customerDetails + email + "'");
			while (rs.next()) {

				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String address = rs.getString("address");
				String zip = rs.getString("zipcode");
				String conNo = rs.getString("contact_no");
				String credit = rs.getString("creditcard");

				request.getSession().setAttribute("fName", fname);
				request.getSession().setAttribute("lName", lname);
				request.getSession().setAttribute("address", address);
				request.getSession().setAttribute("zipcode", zip);
				request.getSession().setAttribute("contactNo", conNo);
				request.getSession().setAttribute("creditCardNo", credit);
				request.getSession().setAttribute("editEmail", email);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/admin.jsp");
	}

}
