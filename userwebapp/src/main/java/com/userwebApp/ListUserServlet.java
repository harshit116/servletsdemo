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


@WebServlet("/listUser")
public class ListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try(Statement statement= connection.createStatement();
		ResultSet result =statement.executeQuery("Select * from user");)
		{
			PrintWriter out=resp.getWriter();
			resp.setContentType("text/html");
			out.println("<table border=1>");
			out.println("<tr>");
			out.println("<th>First Name</th>");
			out.println("<th>Last Name</th>");
			out.println("<th>email Name</th>");
			out.println("</tr>");
			while(result.next())
			{
				String firstName = result.getString(1);
				String lastName = result.getString(2);
				String email = result.getString(3);
				out.println("<tr>");
				out.println("<td>"+firstName+"</td>");
				out.println("<td>"+lastName+"</td>");
				out.println("<td>"+email+"</td>");
				out.println("</tr>");
				out.println();
			}
			out.println("</table>");
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
			
		}
	}
}
	
}
