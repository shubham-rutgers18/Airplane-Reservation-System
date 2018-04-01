package com.flywithme.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flywithme.app.AppConfig;

/**
 * Servlet implementation class EditAccountInformation
 */
public class EditAccountInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAccountInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String phone;
		String address;
		String zipcode;
		String creditcard;
		String firstName=(String)request.getSession().getAttribute("firstName");
		String lastName=(String)request.getSession().getAttribute("lastName");
		String email = (String)request.getSession().getAttribute("email");
		
		int userId=0;
		//Date date = new java.sql.Date(new java.util.Date().getTime());
		ResultSet rs, rs1;
		try {
			rs = AppConfig.getStatement().executeQuery("select u.user_id "
					+ "from user u where  u.email='" + email + "'");

			while (rs.next()) {
				
				userId = Integer.parseInt(rs.getString("user_id"));
				//System.out.println(userId);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			rs1 = AppConfig.getStatement().executeQuery("select address,creditcard,zipcode,contact_no from customer where user_id='"+userId+"'");
			
			while (rs1.next()) {
				address = rs1.getString("address");
				creditcard = rs1.getString("creditcard");
				zipcode = rs1.getString("zipcode");
				phone = rs1.getString("contact_no");
				
				
				request.getSession().setAttribute("address", address);
				request.getSession().setAttribute("zipcode", zipcode);
				request.getSession().setAttribute("contactNo", phone);
				request.getSession().setAttribute("creditCardNo", creditcard);
				
			
			}
			
//			request.getSession().setAttribute("fName", firstName);
//			request.getSession().setAttribute("lName", lastName);
			
			request.getRequestDispatcher("editAccount.jsp").include(request, response);
			
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
