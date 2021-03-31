package com.wipro.sales.bean;

public class SalesReport {
   public String salesID,productID,productName;
   public java.util.Date salesDate;
   public int quantitySold;
   public double productUnitPrice,SalesPricePerUnit,profitAmount;
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
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public java.util.Date getSalesDate() {
	return salesDate;
}
public void setSalesDate(java.util.Date salesDate) {
	this.salesDate = salesDate;
}
public int getQuantitySold() {
	return quantitySold;
}
public void setQuantitySold(int quantitySold) {
	this.quantitySold = quantitySold;
}
public double getProductUnitPrice() {
	return productUnitPrice;
}
public void setProductUnitPrice(double productUnitPrice) {
	this.productUnitPrice = productUnitPrice;
}
public double getSalesPricePerUnit() {
	return SalesPricePerUnit;
}
public void setSalesPricePerUnit(double salesPricePerUnit) {
	SalesPricePerUnit = salesPricePerUnit;
}
public double getProfitAmount() {
	return profitAmount;
}
public void setProfitAmount(double profitAmount) {
	this.profitAmount = profitAmount;
}
}
