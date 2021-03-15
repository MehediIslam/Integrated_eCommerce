package model;
import java.util.Date;
public class Order 
{
 private int orderId,ordererId;
 private double totalCharges,advancePay;
 private Date orderDate,deliveryDate;
 private int receiverId;
 private String status;
 
 public int getOrderId()
 {
	 return orderId;
 }
 public void setOrderId(int OrderId)
 {
	this.orderId = orderId; 
 }
 public int getOrdererId()
 {
	 return ordererId;
 }
 public void setOrdererId(int OrdererId)
 {
	this.ordererId = ordererId; 
 }
	
 public int getReceiverId()
 {
	 return receiverId;
 }
 public void setReceiverId(int receiverId)
 {
	this.receiverId = receiverId; 
 }
 public double getTotalCharges()
 {
	 return totalCharges;
 }
 public void setTotalCharges(double totalCharges)
 {
	this.totalCharges = totalCharges; 
 }
 public double getAdvancePay()
 {
	 return advancePay;
 }
 public void setAdvancePay(double advancePay)
 {
	this.advancePay = advancePay; 
 }
 public Date getOrderDate()
 {
	 return orderDate;
 }
 public void setOrderDate(Date orderDate)
 {
	this.orderDate = orderDate; 
 }
 public Date getDeliveryDate()
 {
	 return deliveryDate;
 }
 public void setDeliveryDate(Date deliveryDate)
 {
	this.deliveryDate = deliveryDate; 
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
