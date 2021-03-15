package model;

public class ProductPurchase 
{
private int id;
private int purchaseId;
private int productId;
private double buyPrice;
private int quantity;
private String status;
public int getId() 
{
	return id;
}
public void setId(int id) 
{
	this.id = id;
}
public int getPurchaseId() 
{
	return purchaseId;
}
public void setPurchaseId(int purchaseId) 
{
	this.purchaseId = purchaseId;
}
public int getProductId() 
{
	return productId;
}
public void setProductId(int productId) 
{
	this.productId = productId;
}
public double getBuyPrice() 
{
	return buyPrice;
}
public void setBuyPrice(double buyPrice) 
{
	this.buyPrice = buyPrice;
}
public int getQuantity() 
{
	return quantity;
}
public void setQuantity(int quantity) 
{
	this.quantity = quantity;
}
public String getStatus() 
{
	return status;
}
public void setStatus(String status) 
{
	this.status = status;
}

}
