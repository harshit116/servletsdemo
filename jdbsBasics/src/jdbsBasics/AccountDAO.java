package jdbsBasics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {
public static void main(String[] args) {
	Connection connection=null;
	Statement statement =null;
	ResultSet rs=null;
	try {
		connection =DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root");
		statement=connection.createStatement();
		rs=statement.executeQuery("select * from account");
		while(rs.next())
		{
			int accno=rs.getInt(1);
			String lastName=rs.getString(2);
			String firstName=rs.getString(3);
			int balance=rs.getInt(4);
			System.out.println(accno+"|"+lastName+"|"+firstName+"|"+balance);
		}
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	finally
	{
		
		try {
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
}
