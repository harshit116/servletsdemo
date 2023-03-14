package com.userwebApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet{

private static final long serialVersionUID=1L;
private Connection connection;
public DeleteUserServlet()
{
super();	
}
 public void init()
 {
	 try {
		Class.forName("com.mysql.jdbc.Driver");
		connection =DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
 }
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String emailid = req.getParameter("emailid");

	
	
	
	try (Statement	statement=connection.createStatement();){
		
		
		
		int result =statement.executeUpdate("delete from user where email='"+ emailid+"'");
		PrintWriter out=resp.getWriter();
		if(result>0)
		{
			out.println("<h1>User deleted in DB</h1>");
			
		}
		else
		{
			out.println("<h1>Error deleting in DB</h1>");

		}
		out.println("<a href=\"list.html\">Home</a>");
		
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	
	
}
public void destroy()
{
if(connection!=null)
{
try {
	connection.close();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}	
}
}
}
