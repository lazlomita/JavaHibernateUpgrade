package com.avantica.test;

import java.sql.*;  

public class TestMysqlConnector {
	
	public static void main(String args[]){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.88.158:3306/hibernate_5_2_17?autoReconnect=true&useSSL=false","hibernate","Control123");
			//192.168.88.158 is the host of MySQL
			//3306 is the port in the host
			//hibernate_3_0_0 is database name
			//hibernate is the database user
			//Control123 is the database user's password
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM employees");
			while(rs.next())
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			con.close();

			System.out.println("********** END OF TEST **********");
		}catch(Exception e){ 
			System.out.println(e);
		}
	}

}
