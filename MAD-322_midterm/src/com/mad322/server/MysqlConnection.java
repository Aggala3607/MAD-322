package com.mad322.server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection 
{
	 private String dbName ="midterm";
	   private String dbuserName = "admin";
	   private String dbPass = "Aggala1234";
	   private String hostName = "database-3.cehj2e64dykn.us-east-2.rds.amazonaws.com";
	   private String dbport = "3306";
	   
	   private String jdbcurl = "jdbc:mysql://"+hostName+":"+dbport+"/"+dbName+"?user="+dbuserName+"&password="+dbPass;
	   
	   private Connection conn = null;
	   public Connection getConnection()
	   {
		   try 
		   {
			   System.out.println("Load Driver");
			   Class.forName("com.mysql.cj.jdbc.Driver");
			   System.out.println("Make Connection To Mysql");
			   conn = DriverManager.getConnection(jdbcurl);
			   return conn;
			   
			   
		   }catch(ClassNotFoundException e)
		   {
			   System.out.println("Connection not found error : "+e.getMessage());
			   
		   }
		   catch(SQLException e)
		   {
			   System.out.println("SQL Exception : "+e.getMessage());
		   }
		   return conn;
	   }
}
