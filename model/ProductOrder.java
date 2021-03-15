package model;

public class ProductOrder 
{
 private int id;
 private int orderId;
 private int productId;
 private double orderPrice;
 private int quantity;
 private String status;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getOrderId() {
	return orderId;
}
public void setOrderId(int orderId) {
	this.orderId = orderId;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public double getOrderPrice() {
	return orderPrice;
}
public void setOrderPrice(double orderPrice) {
	this.orderPrice = orderPrice;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
}
