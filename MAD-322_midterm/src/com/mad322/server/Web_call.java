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
	
@DELETE
@Path("/deleteCusto/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response selectCustomer(@PathParam("id") String id)
{
	MysqlConnection connection= new MysqlConnection();
	conn= connection.getConnection();
	Status status= Status.OK;
	try
	{
		String query="DELETE FROM `customer` WHERE `CUST_ID` = "+id;
		stmt= conn.createStatement();		
		stmt.executeUpdate(query);
		
		int rowCount = stmt.executeUpdate(query);
		if (rowCount > 0) 
		{
		status=Status.OK;
		main.accumulate("status", status);
		main.accumulate("Message","Data successfully updated !");
		System.out.println("Data successfully deleted");
		
		}
		
		
	}catch(SQLException e)
	{
		e.printStackTrace();
		status=Status.NOT_MODIFIED;
		main.accumulate("status",status);
		main.accumulate("Message","Something Went Wrong");
	}
	
	
	return Response.status(status).entity(main.toString()).build();
}
 // INserting into Branch
  
  
  PreparedStatement prparedStatement = null;
    @POST
	@Path("/newBranch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBranch(Branch branch)
	{
		MysqlConnection connection = new MysqlConnection();		
		conn = connection.getConnection();
		
	try
	{	
		String query = "INSERT INTO `midterm`.`branch`(`BRANCH_ID`,`ADDRESS`,`CITY`,`NAME`,`STATE`,`ZIP_CODE`)"
				+ "VALUES(?,?,?,?,?,?)";
		
		prparedStatement = conn.prepareStatement(query);		
		prparedStatement.setInt(1, branch.getBRANCH_ID());
		prparedStatement.setString(2, branch.getADDRESS());
		prparedStatement.setString(3,branch.getCITY());
		prparedStatement.setString(4,branch.getNAME());
		prparedStatement.setString(5,branch.getSTATE());
		prparedStatement.setString(6, branch.getZIP_CODE());		
					
		int rowCount = prparedStatement.executeUpdate();
		
		if(rowCount>0)
		{
			System.out.println("Record inserted Successfully! : "+rowCount);
			
			main.accumulate("Status", 201);
			main.accumulate("Message", "Record Successfully added!");
		}
		
		
	}
	     catch (SQLException e) {

		main.accumulate("Status", 500);
		main.accumulate("Message", e.getMessage());
	}
	     finally {
		  try
		  {
		     	conn.close();
		    	preparedStatement.close();
		  }
		       catch (SQLException e) {
		    	System.out.println("Finally SQL Exception : "+e.getMessage());
		  }
	}		
	return Response.status(201).entity(main.toString()).build();
					
	}
// Displaying from employees whose department id is given from parameters
  @GET
  @Path("/teja/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response teja(@PathParam("id")int id)
  {
 
 
 MysqlConnection connection = new MysqlConnection();
  conn = connection.getConnection();
 
  try
  {
stmt = conn.createStatement();
rs = stmt.executeQuery("select * from employee where DEPT_ID = "+id+";");

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
// Deleting record from the Employee table

@DELETE
@Path("/deleteEmp/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response selectEmployee(@PathParam("id") int id)
{
MysqlConnection connection= new MysqlConnection();
conn= connection.getConnection();
Status status= Status.OK;
try
{
String query="DELETE FROM `employee` WHERE `EMP_ID` = "+id;
stmt= conn.createStatement();
stmt.executeUpdate(query);

int rowCount = stmt.executeUpdate(query);
if (rowCount > 0)
{
status=Status.OK;
main.accumulate("status", status);
main.accumulate("Message","Data successfully updated !");
System.out.println("Data successfully deleted");

}


}catch(SQLException e)
{
e.printStackTrace();
status=Status.NOT_MODIFIED;
main.accumulate("status",status);
main.accumulate("Message","Something Went Wrong");
}


return Response.status(status).entity(main.toString()).build();
}
<<<<<<< HEAD
<<<<<<< HEAD
PreparedStatement preparedStatement = null;
    @POST
	@Path("/newCusto")
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
		
		
	}
	     catch (SQLException e) {

		main.accumulate("Status", 500);
		main.accumulate("Message", e.getMessage());
	}
	     finally {
		  try
		  {
		     	conn.close();
		    	preparedStatement.close();
		  }
		       catch (SQLException e) {
		    	System.out.println("Finally SQL Exception : "+e.getMessage());
		  }
	}		
	return Response.status(201).entity(main.toString()).build();
					
	}
// Inserting into Employee
    
    PreparedStatement prepaStatement = null;
    @POST
	@Path("/newEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEmployee(Employee employee)
	{
		MysqlConnection connection = new MysqlConnection();		
		conn = connection.getConnection();
		
	try
	{	
		String query = "INSERT INTO `midterm`.`employee`(`EMP_ID`,`END_DATE`,`FIRST_NAME`,`LAST_NAME`,`START_DATE`,`TITLE`,`ASSIGNED_BRANCH_ID`,`DEPT_ID`,`SUPERIOR_EMP_ID`)"
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		
		preparedStatement = conn.prepareStatement(query);		
		preparedStatement.setInt(1, employee.getEMP_ID());
		preparedStatement.setString(2, employee.getEND_DATE());
		preparedStatement.setString(3,employee.getFIRST_NAME());
		preparedStatement.setString(4,employee.getLAST_NAME());
		preparedStatement.setString(5,employee.getSTART_DATE());
		preparedStatement.setString(6, employee.getTITLE());
		preparedStatement.setInt(7, employee.getASSIGNED_BRANCH_ID());
		preparedStatement.setInt(8, employee.getDEPT_ID());
		preparedStatement.setInt(9, employee.getSUPERIOR_EMP_ID());
					
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
		
		
	}
	     catch (SQLException e) {

		main.accumulate("Status", 500);
		main.accumulate("Message", e.getMessage());
	}
	     finally {
		  try
		  {
		     	conn.close();
		    	preparedStatement.close();
		  }
		       catch (SQLException e) {
		    	System.out.println("Finally SQL Exception : "+e.getMessage());
		  }
	}		
	return Response.status(201).entity(main.toString()).build();
					
	}
=======
@DELETE
@Path("/deleteBranch/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response selectBranch(@PathParam("id") int id)
{
	MysqlConnection connection= new MysqlConnection();
	conn= connection.getConnection();
	Status status= Status.OK;
	try
	{
		String query="DELETE FROM `branch` WHERE `BRANCH_ID` = "+id;
		stmt= conn.createStatement();		
		stmt.executeUpdate(query);
		
		int rowCount = stmt.executeUpdate(query);
		if (rowCount > 0) 
		{
		status=Status.OK;
		main.accumulate("status", status);
		main.accumulate("Message","Data successfully updated !");
		System.out.println("Data successfully deleted");
		
		}
		
		
	}catch(SQLException e)
	{
		e.printStackTrace();
		status=Status.NOT_MODIFIED;
		main.accumulate("status",status);
		main.accumulate("Message","Something Went Wrong");
	}
	
	
	return Response.status(status).entity(main.toString()).build();
}
// Updating the information in Employee table 
    
    PreparedStatement preparStatement = null;
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/editEmp/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id")int id,Employee emp)
    {
    	MysqlConnection connection= new MysqlConnection();
    	conn= connection.getConnection();
    	Status status =Status.OK;
    	try {
    		String query ="UPDATE `employee` SET `EMP_ID` =?,`END_DATE` =?,`FIRST_NAME` =?,`LAST_NAME` =?, `START_DATE` =?,`TITLE` =?,`ASSIGNED_BRANCH_ID` =?,`DEPT_ID` =?,`SUPERIOR_EMP_ID` =? WHERE `EMP_ID` ="+id;
    		
    
    		preparStatement = conn.prepareStatement(query);   		
    		   		
    		preparStatement.setInt(1, emp.getEMP_ID());
    		preparStatement.setString(2, emp.getEND_DATE());
    		preparStatement.setString(3,emp.getFIRST_NAME());
    		preparStatement.setString(4,emp.getLAST_NAME());
    		preparStatement.setString(5,emp.getSTART_DATE());
    		preparStatement.setString(6, emp.getTITLE());
    		preparStatement.setInt(7, emp.getASSIGNED_BRANCH_ID());  
    		preparStatement.setInt(8, emp.getDEPT_ID());
    		preparStatement.setInt(9, emp.getSUPERIOR_EMP_ID());
    		

    		int rowCount = preparStatement.executeUpdate();
    		
    		if (rowCount > 0) 
    		{
    		status=Status.OK;
    		main.accumulate("status", status);
    		main.accumulate("Message","Data successfully updated !");
    		
    		}
    		else
    		{
    			status=Status.NOT_MODIFIED;
    			main.accumulate("status", status);
    			main.accumulate("Message","Something Went Wrong");
    						
    		}
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    		status=Status.NOT_MODIFIED;
    		main.accumulate("status", status);
    		main.accumulate("Message","Something Went Wrong");
    	}
    	
    	return Response.status(status).entity(main.toString()).build();
    }
>>>>>>> 1b0dd76c9fe17bfe4371dc00c30589a7e658f63a
=======

>>>>>>> 2407078580c9afd0dc3419f8be5eb655a973bd4b
}

  