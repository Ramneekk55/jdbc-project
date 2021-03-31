package com.wipro.sales.main;
import java.util.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.wipro.sales.service.Administrator;
import com.wipro.sales.bean.Product;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.bean.Sales;
public class SalesApplication {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    System.out.println("1. Insert Stock");
    System.out.println("2. Delete Stock");
    System.out.println("3. Insert Sales");
    System.out.println("4. View Sales Report");
    System.out.println("Enter your choice:");
    Scanner s=new Scanner(System.in);
    int c=s.nextInt();
    switch(c) {
    case 1:
    	Product ob=new Product();
    	System.out.print("Enter product name: ");
    	ob.productName=s.next();
    	System.out.println("Enter Quantity on hand: ");
    	ob.quantityOnHand=s.nextInt();
    	System.out.println("Enter Product Unit Price: ");
    	ob.productUnitPrice=s.nextDouble();
    	System.out.println("Enter reorder level");
    	ob.reorderLevel=s.nextInt();
    	Administrator ob1=new Administrator();
    	String str=ob1.insertStock(ob);
        System.out.println(str);
        break;
    case 2:
    	System.out.println("Enter productID");
    	String s1=s.next();
    	Administrator ob2=new Administrator();
    	String str1=ob2.deleteStock(s1);
    	System.out.println(str1);
    	break;
    case 3:
    	Administrator ob3=new Administrator();
    	Sales o=new Sales();
    	System.out.print("Enter productID: ");
    	o.productID=s.next();
    	try{
    	SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    	System.out.println("Enter Sales Date ");
    	String dtString=s.next();
    	java.util.Date dt= dateF.parse(dtString);
    	java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
    	o.salesDate=(sqlDate);
    	}
    	
    	catch(Exception e) {
    		System.out.println(" ");
    	}
    	System.out.println("Enter Quantity Sold ");
    	o.quantitySold=s.nextInt();
    	System.out.println("Enter Sales Price Per Unit");
    	o.salesPricePerUnit=s.nextDouble();
    	String s2=ob3.insertSales(o);
    	System.out.println(s2);
    	break;
    case 4:
    	Administrator ob4=new Administrator();
    	ArrayList<SalesReport> a=ob4.getSalesReport();
    	int j;
    	 System.out.println("SalesID  ProductID  ProductName  SalesDate  QtySold  ProdUnitPrice  SalesPricePerUnit  ProfitAmount");
    	for (j = 0; j < a.size(); j++) 
        {
    		 SalesReport data = a.get(j); 
    		 System.out.println(data.salesID+"   "+data.productID+"   "+data.productName+"   "+data.salesDate+"   "+
    		 data.quantitySold+"   "+data.productUnitPrice+"   "+data.SalesPricePerUnit+"   "+data.profitAmount);
        }
          break;
        }  
    }
	}


