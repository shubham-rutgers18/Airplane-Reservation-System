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

/**
 * Servlet implementation class ReservationByFlightServlet
 */
public class ReservationByFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String month = request.getParameter("Month");
	        String year = request.getParameter("Year");
	        String MonthlyRev = null;
	        PreparedStatement ps = AppConfig.getPreparedStatement(MonthlyRev);
	        
	        ResultSet MonthlyR;
	        
	        try {
	            ps.setString(1, month);
	            ps.setString(2, year);
	            MonthlyR = ps.executeQuery();
	            while (MonthlyR.next()) {
	                MonthlyRev = MonthlyR.getString("*************************from Sql query*******************************************************");
	            }
	            request.setAttribute("MonthlyRev", MonthlyRev);
	        } catch (SQLException e) {
	            // todo
	        }
	}

}
