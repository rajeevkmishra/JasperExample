package com.java.util;

import java.sql.*;



public class JDBCConnection {

	public static Connection getJDBCConnection() throws SQLException
	{
		
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?characterEncoding=latin1&useConfigs=maxPerformance","root","root");  
		return con;
	}
	
	public static void main(String args[]) throws SQLException
	{
		System.out.println("hi");
		Connection con = JDBCConnection.getJDBCConnection();
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from emp");  
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		con.close();  
	}
	
}
