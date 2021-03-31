package com.wipro.sales.service;
import java.sql.Connection;
import java.util.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.Product;
import com.wipro.sales.dao.StockDao;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.dao.SalesDao;
import java.util.ArrayList;
public class Administrator {
   public String insertStock(Product Stockobj) {
	  if(Stockobj==null) {
		  return "Data not valid for insertion";
	  }
	  else if(Stockobj.productName.length()<2) {
		  return "Data not valid for insertion";
	  }
	  else {
		  StockDao b=new StockDao();
		  String str=b.generateProductID(Stockobj.productName);
		  Stockobj.setProductID(str);
		 int d= b.insertStock(Stockobj);
		 if(d==1) {
			 System.out.println("Data added succuessfully");
		 }
		 else {
			 System.out.println("Error");
		 }
		  return str;
	  }
   }
   public String deleteStock(String productID) {
	   StockDao b=new StockDao();
	   int d=b.deleteStock(productID);
	   if(d==1) {
		   return "Deleted";
	   }
	   else {
		   return "Record cannot be deleted";
	   }
   }
   public String insertSales(Sales Salesobj) {
	  // java.util.Date date,currentDate;
	   if(Salesobj==null) {
		   return "Object not valid for insertion";
	   }
	     int q=0;
	 //  Date date = Salesobj.salesDate;
	    java.sql.Date sqlDate = new java.sql.Date(Salesobj.salesDate.getTime());
	    Date curDate = new Date(new java.util.Date().getTime());
	    PreparedStatement ps;
	       try {
		   Class.forName("com.mysql.cj.jdbc.Driver"); 
		   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc project","root","");
		   ps=con.prepareStatement("SELECT COUNT(Product_ID) FROM tbl_stock where Product_ID=?");
		   ps.setString(1,Salesobj.productID);
		   ResultSet rs=ps.executeQuery();
		   int d=0;
		   while(rs.next())
		   {
			  d= rs.getInt("COUNT(Product_ID)");
		   }
		   if(d==0) {
			   return "Unknown Product for sales";
		   }
		   ps=con.prepareStatement("select Quantity_On_Hand from tbl_stock where Product_ID=?");
		   ps.setString(1,Salesobj.productID);
		   rs=ps.executeQuery();  
		   while(rs.next()){  
		   q=rs.getInt("Quantity_On_Hand");  
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
	     //  Product ob=new Product();
	       if(q<Salesobj.quantitySold) {
	    	   return "Not enough stock on hand for sales";
	       }
	      // date= new java.sql.Date(Salesobj.getSalesDate().getTime());
	     //  currentDate=new java.sql.Date(new java.util.Date().getTime());
	       if(sqlDate.compareTo(curDate)>0)                                 
	       {
	        return "Invalid Date";
	       }
	       else {
	    	   SalesDao ob1=new SalesDao();
	    	   String str2=ob1.generateSalesID(sqlDate);
	    	   Salesobj.salesID=str2;
	    	   int d=ob1.insertSales(Salesobj);
	    	   if(d==1) {
	    		   StockDao ob2=new StockDao();
	    		   int e=ob2.updateStock(Salesobj.productID, Salesobj.quantitySold);
	    		   if(e==1) {
	    			   return "Sales completed";
	    		   }
	    		   else {
	    			   return "Error";
	    		   }
	    	   }
	    	   else {
	    		   return "Error";
	    	   }
	       }
   }
   public ArrayList<SalesReport> getSalesReport(){
	   SalesDao ob=new SalesDao();
	   ArrayList<SalesReport> a=new ArrayList<SalesReport>();
	   a=ob.getSalesReport();
	   return a;
   }
}
