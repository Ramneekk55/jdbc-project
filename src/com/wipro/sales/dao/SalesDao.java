package com.wipro.sales.dao;
import com.wipro.sales.bean.SalesReport;
//import java.util.Date;
import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import com.wipro.sales.bean.Product;
import com.wipro.sales.bean.Sales;
public class SalesDao{
     public int insertSales(Sales sales1) {
    	 sales1.setSalesID(sales1.salesID);
    	 sales1.setSalesDate(sales1.salesDate);
    	 sales1.setProductID(sales1.productID);
    	 sales1.setQuantitySold(sales1.quantitySold);
    	 sales1.setSalesPricePerUnit(sales1.salesPricePerUnit);
    	 Connection con=null;
    	 PreparedStatement ps;
    	// java.util.Date myDate = new java.util.Date("10/10/2009");
    	 //java.sql.Date sqlDate = new java.sql.Date(sales1.salesDate);
    	  int t=0;
    	  try
    	  {
    	   Class.forName("com.mysql.cj.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc project?serverTimezone=UTC","root","");
    	   ps=con.prepareStatement("insert into tbl_sales values(?,?,?,?,?)");
    	   ps.setString(1,sales1.getSalesID());
    	   ps.setDate(2,sales1.getSalesDate());
    	   ps.setString(3,sales1.getProductID());
    	   ps.setInt(4,sales1.getQuantitySold());
    	   ps.setDouble(5,sales1.getSalesPricePerUnit());
    	   t=ps.executeUpdate();
    	  }
    	  catch (ClassNotFoundException e) {
    		  // e.printStackTrace();
    		  System.out.print("");
    		  }
    	  catch(SQLException e)
    	  {
    	   e.printStackTrace();
    	   //return "FAIL";
    	   System.out.print("");
    	  }
    	  return 1;
    	 }
     public String generateSalesID(java.sql.Date salesDate) {
    	 Calendar calendar = Calendar.getInstance();
    	 calendar.setTime(salesDate);
    	 int d=calendar.get(Calendar.YEAR);
    	 d=d%10;
    	 int v=0;
    	 int t=0;
    	 Connection con=null;
    	 PreparedStatement ps=null;
    	 try {
    		 Class.forName("com.mysql.cj.jdbc.Driver");
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc project?serverTimezone=UTC","root","");
           //  select <seq_id>.CURRVAL from dual;
           //  ps=con.prepareStatement("select <seq_id>.CURRVAL from seq");
             Statement st=con.createStatement();
             ResultSet rs=st.executeQuery("SHOW TABLE STATUS LIKE 'seq' ");
             while(rs.next())
             {
          	  v= rs.getInt("AUTO_INCREMENT");
             }
    	 }
    	 catch (ClassNotFoundException e) {
    		   // TODO Auto-generated catch block
    		   e.printStackTrace();
    		  
    		  }
    		  catch (SQLException e) {
    		   // TODO Auto-generated catch block
    		   //e.printStackTrace();
    			  System.out.print("");
    		  }
    	 String str=d+""+v;
    	 return str;
     }
     public ArrayList<SalesReport> getSalesReport(){
      Connection con;
   	  PreparedStatement ps;
   	  ArrayList<SalesReport> a=new ArrayList<SalesReport>();
   	  try
   	  {
   	   Class.forName("com.mysql.cj.jdbc.Driver");
   	   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc project?serverTimezone=UTC","root","");
   	   ps=con.prepareStatement("select * from V_SALES_REPORT");
   	   ResultSet rs=ps.executeQuery();  
       while(rs.next()){  
    	   SalesReport sales = new SalesReport();
           sales.setSalesID(rs.getString(1));
           sales.setSalesDate(rs.getDate(2));
           sales.setProductID(rs.getString(3));
           sales.setProductName(rs.getString(4));
           sales.setQuantitySold(rs.getInt(5));
           sales.setProductUnitPrice(rs.getDouble(6));
           sales.setSalesPricePerUnit(rs.getDouble(7));
           sales.setProfitAmount(rs.getDouble(8));
           
           a.add(sales);
    }  
     }
   	catch (ClassNotFoundException e) {
 	   // TODO Auto-generated catch block
 	   e.printStackTrace();
 	  
 	  }
 	  catch (SQLException e) {
 	   // TODO Auto-generated catch block
 	   e.printStackTrace();
 	  }
   	  return a;
}
}
