package com.mad322.server;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("/webcall")
public class Web_call 
{
	Connection conn = null;
	Statement stmt =  null;
	ResultSet rs = null;
	
	  JSONObject main = new JSONObject();
	  JSONArray  jsArray = new JSONArray();
	  JSONObject child = new JSONObject();
	
	
  @GET
  @Path("/chandra")
  @Produces(MediaType.APPLICATION_JSON)
  public Response chandra()
  {
	  
	  
	  MysqlConnection connection = new MysqlConnection();
	   conn = connection.getConnection();
	   
	   try 
	   {
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from employee;");
		
		while(rs.next())
		{
		  child = new JSONObject();
		  
		  
		  child.accumulate("Employee Name", rs.getString("FIRST_NAME"));	
		  child.accumulate("Department ID", rs.getString("DEPT_ID"));
		
		 		  
		  jsArray.put(child);
		}
		
		main.put("employee", jsArray);
		
	   }catch(SQLException e)
		{
			System.out.println("SQL Exception : +e.getMessage");
		}
		finally
		{
			try
			{
				conn.close();
				stmt.close();
				rs.close();
			}
			catch (SQLException e)
			{
				System.out.println("Finally Block SQL Exception : "+e.getMessage());
			}
			
		}
	
	return Response.status(200).entity(main.toString()).build();
	  	  

  }
  PreparedStatement preparedStatement = null;
    @POST
	@Path("/createEmp")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCustomer(Customer customer)
	{
		MysqlConnection connection = new MysqlConnection();		
		conn = connection.getConnection();
		
	try
	{	
		String query = "INSERT INTO `midterm`.`customer`(`CUST_ID`,`ADDRESS`,`CITY`,`CUST_TYPE_CD`,`FED_ID`,`POSTAL_CODE`,`STATE`)"
				+ "VALUES(?,?,?,?,?,?,?)";
		
		preparedStatement = conn.prepareStatement(query);		
		preparedStatement.setInt(1, customer.getCUST_ID());
		preparedStatement.setString(2, customer.getADDRESS());
		preparedStatement.setString(3,customer.getCITY());
		preparedStatement.setString(4,customer.getCUST_TYPE_CD());
		preparedStatement.setString(5,customer.getFED_ID());
		preparedStatement.setInt(6, customer.getPOSTAL_CODE());
		preparedStatement.setString(7, customer.getSTATE());
	
		
		
		int rowCount = preparedStatement.executeUpdate();
		
		if(rowCount>0)
		{
			System.out.println("Record inserted Successfully! : "+rowCount);
			
			main.accumulate("Status", 201);
			main.accumulate("Message", "Record Successfully added!");
		}else
		{
			main.accumulate("Status", 500);
			main.accumulate("Message", "Something went wrong!");
		}
		
		
	}catch (SQLException e) {

		main.accumulate("Status", 500);
		main.accumulate("Message", e.getMessage());
	}finally {
		try
		{
			conn.close();
			preparedStatement.close();
		}catch (SQLException e) {
			System.out.println("Finally SQL Exception : "+e.getMessage());
		}
	}
	
	
	return Response.status(201).entity(main.toString()).build();
	
		
		
	}
	
 
 
}
  