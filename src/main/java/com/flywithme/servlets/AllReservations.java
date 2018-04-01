package com.flywithme.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flywithme.app.AppConfig;

/**
 * Servlet implementation class AllReservations
 */
public class AllReservations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllReservations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			rs1 = AppConfig.getStatement().executeQuery("select r.airline_id,r.flight_id,r.reservation_id,r.booking_fee,"
					+ "r.reservation_date,r.seats_reserved "
					+ "from customerreservations c "
					+ "join reservations r on r.reservation_id=c.reservation_id "
					+ "where  c.user_id ='" + userId + "' group by r.reservation_id order by r.reservation_date desc");
			
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			out.println("<table><thead><tr><td>Reservation no</td><td>Airline</td><td>Flight</td><td>Date</td><td>Seats</td><td>Fee</td><td></td></tr></thead><tbody>");
			
			while(rs1.next()) {
				String airline=(rs1.getString("airline_id"));
				String flight=(rs1.getString("flight_id"));
				String d=(rs1.getString("reservation_date"));
				String fee=(rs1.getString("booking_fee"));
				String id=(rs1.getString("reservation_id"));
				String seats=(rs1.getString("seats_reserved"));
				
				out.println("<tr><td>"+id+"</td><td>"+airline+"</td><td>"+flight+"</td><td>"+d+"</td><td>"+seats+"</td><td>"+fee+"</td>"
						+ "<td><form method='POST' action='showSchedule'><button type='submit' name='reservationButton' id='reservationButton' class=\"waves-effect waves-light btn\" value="+id+">view</button></form></td></tr>");
				
			}
			out.println("</tbody></table>");
//			request.setAttribute("current_list", reservation_list);
//			request.getRequestDispatcher("user.jsp").include(request, response);
//			System.out.println(reservation_list);

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
