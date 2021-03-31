package com.wipro.sales.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wipro.sales.bean.Product;
public class StockDao {
  public int insertStock(Product sales) {
	  sales.setProductID(sales.productID);
	  sales.setProductName(sales.productName);
	  sales.setQuantityOnHand(sales.quantityOnHand);
	  sales.setProductUnitPrice(sales.productUnitPrice);
	  sales.setReorderLevel(sales.reorderLevel);
	  Connection con=null;
 	 PreparedStatement ps;
 	  int t=0;
 	  try
 	  {
 	   Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc project?serverTimezone=UTC","root","");
 	   ps=con.prepareStatement("insert into tbl_stock values(?,?,?,?,?)");
 	   ps.setString(1,sales.getProductID());
 	   ps.setString(2,sales.getProductName());
 	   ps.setInt(3,sales.getQuantityOnHand());
 	   ps.setDouble(4, sales.getProductUnitPrice());
 	   ps.setDouble(5,sales.getReorderLevel());
 	   t=ps.executeUpdate();
 	   
 	  }
 	  catch (ClassNotFoundException e) {
 		   e.printStackTrace();
 		  
 		  }
 	  catch(SQLException e)
 	  {
 	   e.printStackTrace();
 	   //return "FAIL";
 	  }
 	  return 1;
  }
  public String generateProductID(String productName) {
	  char arr[]=productName.toCharArray();
	  String s=arr[0]+""+arr[1];
	  Connection con=null;
 	 PreparedStatement ps=null;
 	 int d=0,t=0;
 	 try {
 		 Class.forName("com.mysql.cj.jdbc.Driver");
          con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc project?serverTimezone=UTC","root","");
        //  select <seq_id>.CURRVAL from dual;
          Statement st=con.createStatement();
          ResultSet rs=st.executeQuery("SHOW TABLE STATUS LIKE 'seq' ");
          while(rs.next())
          {
       	  d= rs.getInt("AUTO_INCREMENT");
          }
        //Inserting values so as to increase auto_increment in seq table
          ps=con.prepareStatement("insert into seq values(?,?)");
          ps.setInt(1,d);
          ps.setString(2,"B");
          t=ps.executeUpdate();
 	 }
 	 catch (ClassNotFoundException e) {
 		   // TODO Auto-generated catch block
 		   e.printStackTrace();
 		  
 		  }
 		  catch (SQLException e) {
 		   // TODO Auto-generated catch block
 		   e.printStackTrace();
 		  }
 	 String str=s+"" +d;
 	 return str;
  }
  public int updateStock(String productID,int soldQty) {
	  Connection con;
	  PreparedStatement ps;
	  Product obj=new Product();
	  int currentQuan=obj.getQuantityOnHand();    //get current quantity
	  int remainingQuan=currentQuan-soldQty;            //calculate remaining quantity  
	  int t=0;
	  try
	  {
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc project?serverTimezone=UTC","root","");
	   ps=con.prepareStatement("update tbl_stock set=? where Product_ID = ?");
	   ps.setInt(1,remainingQuan);
	   ps.setString(2,productID);
	   t=ps.executeUpdate();
	  }
	  catch (ClassNotFoundException e) {
		   // TODO Auto-generated catch block
		  // e.printStackTrace();
		  System.out.print("");
		  }
		  catch (SQLException e) {
		   // TODO Auto-generated catch block
		   //e.printStackTrace();
			  System.out.print("");
		  }
	  return 1;
  }
  public Product getStock(String productID) {
	  Connection con;
	  PreparedStatement ps;
	  Product p=null;
	  int t=0;
	  try
	  {
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc project?serverTimezone=UTC","root","");
	   ps=con.prepareStatement("select * from tbl_stock where Product_ID = ?");
	   ps.setString(1,productID);
	   ResultSet rs=ps.executeQuery();  
	   while(rs.next()){ 
			   p.productID=rs.getString(1);
			   p.productName=rs.getString(2);
			   p.productUnitPrice=rs.getDouble(3);
			   p.quantityOnHand=rs.getInt(4);
			   p.reorderLevel=rs.getInt(5);
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
	  return p;
  }
  public int deleteStock(String productID) {
	  Connection con;
	  PreparedStatement ps;
	  int t=0;
	  try
	  {
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc project?serverTimezone=UTC","root","");
	   ps=con.prepareStatement("delete from tbl_stock where Product_ID= ?");
	   ps.setString(1,productID);
	   t=ps.executeUpdate();
	  }
	  catch (ClassNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  
		  }
		  catch (SQLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
	  return 1;
  }
}
