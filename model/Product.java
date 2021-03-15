package model;
import java.util.Date;
public class Product
{
 private int productId;
 private int quantity;
 private int brandId;
 private int supplierId;
 private int catId;
 private String productName;
 private double buyPrice;
 private double sellPrice;
 private String imageLocation;
 private String description;
 private Date lastPurchased;
 private Date lastSold;
public int getProductId() 
{
	return productId;
}
public void setProductId(int productId) 
{
	this.productId = productId;
}
public int getQuantity() 
{
	return quantity;
}
public void setQuantity(int quantity) 
{
	this.quantity = quantity;
}
public int getBrandId() 
{
	return brandId;
}
public void setBrandId(int brandId) 
{
	this.brandId = brandId;
}
public int getSupplierId() 
{
	return supplierId;
}
public void setSupplierId(int supplierId) 
{
	this.supplierId = supplierId;
}
public int getCatId() 
{
	return catId;
}
public void setCatId(int catId) 
{
	this.catId = catId;
}
public String getProductName() 
{
	return productName;
}
public void setProductName(String productName) 
{
	this.productName = productName;
}
public double getBuyPrice() 
{
	return buyPrice;
}
public void setBuyPrice(double buyPrice) 
{
	this.buyPrice = buyPrice;
}
public double getSellPrice() 
{
	return sellPrice;
}
public void setSellPrice(double sellPrice) 
{
	this.sellPrice = sellPrice;
}
public Date getLastPurchased() 
{
	return lastPurchased;
}
public void setLastPurchased(Date lastPurchased) 
{
	this.lastPurchased = lastPurchased;
}
public Date getLastSold() 
{
	return lastSold;
}
public void setLastSold(Date lastSold) 
{
	this.lastSold = lastSold;
}


public String getDescription() 
{
	return description;
}
public void setDescription(String description) 
{
	this.description = description;
}

public String getImgLocation() 
{
	return imageLocation;
}
public void setImgLocation(String imageLocation) 
{
	this.imageLocation = imageLocation;
}

}
