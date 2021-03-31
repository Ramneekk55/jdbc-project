package com.wipro.sales.bean;

public class Sales {
   public String salesID,productID;
   public java.sql.Date salesDate;
   public int quantitySold;
   public double salesPricePerUnit;
public String getSalesID() {
	return salesID;
}
public void setSalesID(String salesID) {
	this.salesID = salesID;
}
public String getProductID() {
	return productID;
}
public void setProductID(String productID) {
	this.productID = productID;
}
public java.sql.Date getSalesDate() {
	return salesDate;
}
public void setSalesDate(java.sql.Date salesDate) {
	this.salesDate = salesDate;
}
public int getQuantitySold() {
	return quantitySold;
}
public void setQuantitySold(int quantitySold) {
	this.quantitySold = quantitySold;
}
public double getSalesPricePerUnit() {
	return salesPricePerUnit;
}
public void setSalesPricePerUnit(double salesPricePerUnit) {
	this.salesPricePerUnit = salesPricePerUnit;
}
   
}
