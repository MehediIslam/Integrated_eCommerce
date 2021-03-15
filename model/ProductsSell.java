package model;

public class ProductsSell 
{
private int id;
private int sellId;
private int productId;
private double sellPrice;
private int quantity;
public int getId() 
{
	return id;
}
public void setId(int id) 
{
	this.id = id;
}
public int getSellId() 
{
	return sellId;
}
public void setSellId(int sellId) 
{
	this.sellId = sellId;
}
public int getProductId() 
{
	return productId;
}
public void setProductId(int productId) 
{
	this.productId = productId;
}
public double getSellPrice() 
{
	return sellPrice;
}
public void setSellPrice(double sellPrice) 
{
	this.sellPrice = sellPrice;
}
public int getQuantity() 
{
	return quantity;
}
public void setQuantity(int quantity) 
{
	this.quantity = quantity;
}
}
