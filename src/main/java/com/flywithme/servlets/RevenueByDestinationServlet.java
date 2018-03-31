package com.flywithme.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flywithme.app.AppConfig;

public class RevenueByDestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String city = request.getParameter("city");
	        String revenue = null;
	        PreparedStatement ps = AppConfig.getPreparedStatement(null);
	        

	        ResultSet Drevenue;
	        try {
	            ps.setString(1, city);
	            Drevenue = ps.executeQuery();
	            while (Drevenue.next()) {
	                revenue = Drevenue.getString("***************************************From Sql Query*********************");
	            }
	            request.setAttribute("revenue", revenue);
	        } catch (SQLException e) {
	            // todo
	        }
	}

}
