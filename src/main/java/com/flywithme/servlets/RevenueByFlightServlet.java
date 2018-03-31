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

public class RevenueByFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String flightrevenue = null;
	//**********************************************************************************sql Query***************************
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flightid = request.getParameter("flightid");
		String Frevenue = null;
		PreparedStatement ps = AppConfig.getPreparedStatement(flightrevenue);// sql query is not written for this 

		ResultSet frevenue;
		try {
			ps.setString(1, flightid);
			frevenue = ps.executeQuery();
			while (frevenue.next()) {
				Frevenue = frevenue.getString("****************************************sql***********");
			}
			request.setAttribute("Frevenue", Frevenue);
		} catch (SQLException e) {
			// todo
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
