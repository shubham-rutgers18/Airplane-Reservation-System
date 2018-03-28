package com.flywithme.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class AppConfig {

	private static final String HOSTNAME = "jdbc:mysql://dbms1.ckz0sfiltvup.us-east-2.rds.amazonaws.com:3306/flywithme";
	private static final String USERNAME = "shubham";
	private static final String PASSWORD = "bitan1994";

	public static Statement getConnectionStatement() {

		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);
			st = con.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;

	}

	public static StrongPasswordEncryptor passwordEncryptor() {
		return new StrongPasswordEncryptor();
	}

	public static boolean checkPassword(String pass1, String pass2) {
		return passwordEncryptor().checkPassword(pass1, pass2);
	}
}
