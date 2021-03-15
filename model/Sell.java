package model;

public class Sell 
{
 private int sellId;
 private String buyerId;
 private double totalCharges;
 private double paidAmount;
 private String sellDate;
 public int getSellId() 
{
	return sellId;
}
public void setSellId(int sellId)
{
	this.sellId = sellId;
}
public String getBuyerId()
{
	return buyerId;
}
public void setBuyerId(String phone)
{
	this.buyerId = phone;
}
public double getTotalCharges()
{
	return totalCharges;
}
public void setTotalCharges(double totalCharges)
{
	this.totalCharges = totalCharges;
}
public double getPaidAmount()
{
	return paidAmount;
}
public void setPaidAmount(double paid)
{
	this.paidAmount = paid;
}
public String getSellDate()
{
	return sellDate;
}
public void setSellDate(String sellingDate)
{
	this.sellDate = sellingDate;
}
public int getSellerId()
{
	return sellerId;
}
public void setSellerId(int sellerId)
{
	this.sellerId = sellerId;
}
private int sellerId;
	
	
}
