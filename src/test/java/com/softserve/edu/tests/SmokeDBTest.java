package com.softserve.edu.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SmokeDBTest {
	private Connection con = null;
	private String username = "qc";
	private String password = "pass";
	private String URL = "jdbc:mysql://localhost:3306/maqc";

	@BeforeClass
	public void oneTimeSetUp() throws Exception {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		con = DriverManager.getConnection(URL, username, password);
		if (con != null) {
			System.out.println("Connection Successful! \n");
		}
	}

	@AfterClass
	public void oneTimeTearDown() throws Exception {
		if (con != null) {
			con.close();
		}
	}

	@Test
	public void checkConnection() throws Exception {
		Statement st = con.createStatement();
        //st.execute("DELETE FROM users WHERE login='pet';");
		st.execute("DELETE FROM users;");
		System.out.println("DONE");
		//
		// For Select
		/*
		ResultSet rs = st.executeQuery("select * from users;");
		int columnCount = rs.getMetaData().getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= columnCount; i++) {
				System.out.print(rs.getString(i) + "\t");
			}
			System.out.println();
		}
		if (rs != null) {
			rs.close();
		}
		*/
		if (st != null) {
			st.close();
		}
	}
}
