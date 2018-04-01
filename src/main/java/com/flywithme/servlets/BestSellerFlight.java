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
 * Servlet implementation class BestSellerFlight
 */
public class BestSellerFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BestSellerFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String airline="";
		String flight="";
		String fl="";
		String a1="";
		String dt="";
		String a2="";
		String at="";
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		ResultSet rs;
		try {
//			System.out.println("select a.airline_name, f.flight_id, f.flightleg_id, a1.airport_name as origin,"
//					+ "f.scheduled_departure,a2.airport_name as dest, f.scheduled_arrival "
//					+ "from reservations r "
//					+ "join flightleg f on f.airline_id=r.airline_id and f.flight_id=r.flight_id and f.flightleg_id=r.flightleg_id "
//					+ "join airport a1 on a1.airport=f.origin_airport_id "
//					+ "join airport a2 on a2.airport_id=f.destination_airport_id "
//					+ "join airline a on a.airline_id=r.airline_id "
//					+ "join (select airline_id,flight_id,flightleg_id,count(*) as c " + 
//							"from reservations " + 
//							"group by airline_id,flight_id,flightleg_id " + 
//							"order by c desc limit 1)t1 on t1.airline_id=r.airline_id and t1.flight_id=r.flight_id and t1.flightleg_id=r.flightleg_id");
			rs = AppConfig.getStatement().executeQuery("select a.airline_name, f.flight_id, f.flightleg_id, a1.airport_name as origin,"
					+ "f.scheduled_departure,a2.airport_name as dest, f.scheduled_arrival "
					+ "from reservations r "
					+ "join flightleg f on f.airline_id=r.airline_id and f.flight_id=r.flight_id and f.flightleg_id=r.flightleg_id "
					+ "join airport a1 on a1.airport_id=f.origin_airport_id "
					+ "join airport a2 on a2.airport_id=f.destination_airport_id "
					+ "join airline a on a.airline_id=r.airline_id "
					+ "join (select airline_id,flight_id,flightleg_id,count(*) as c " + 
							"from reservations " + 
							"group by airline_id,flight_id,flightleg_id " + 
							"order by c desc limit 1)t1 on t1.airline_id=r.airline_id and t1.flight_id=r.flight_id and t1.flightleg_id=r.flightleg_id"
					);
			

			while (rs.next()) {
				airline=(rs.getString("airline_name"));
				flight=(rs.getString("flight_id"));
				fl=(rs.getString("flightleg_id"));
				a1=(rs.getString("origin"));
				dt=(rs.getString("scheduled_departure"));
				a2=(rs.getString("dest"));
				at=(rs.getString("scheduled_arrival"));
				
			}
			out.println("<h3>Best selling flight is: "+airline +" Flight no: "+flight+" Flightleg: "+fl+" </h3>");
			out.println("<h4>Origin: "+a1+" Departing at: "+dt+"</h4>");
			out.println("<h4>Destination: "+a2+" Arriving at: "+at+"</h4>");

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
