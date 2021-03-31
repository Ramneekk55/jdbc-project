package com.wipro.sales.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
   public static Connection getDBConnection() {
	   Connection con=null;
	   try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Table1","root","");
	   }
	   catch(Exception e){
		   System.out.print(e);
	   }
	   return con;
   }
}
