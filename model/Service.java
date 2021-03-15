package model;
import java.util.Date;
public class Service 

{
 private int serviceId;
 private String customerId;
 private double totalCharges;
 private double AdvancePay;
 private Date receiveDate;
 private int receiverId;
 private Date deliveryDate;
 private String status;
 
 
public int getServiceId()
{
	return serviceId;
}
public void setServiceId(int serviceId)
{
	this.serviceId = serviceId;
}
public String getCustomerId()
{
	return customerId;
}
public void setCustomerId(String customerId)
{
	this.customerId = customerId;
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
	return AdvancePay;
}
public void setAdvancePay(double advancePay)
{
	AdvancePay = advancePay;
}
public Date getReceiveDate()
{
	return receiveDate;
}
public void setReceiveDate(Date receiveDate)
{
	this.receiveDate = receiveDate;
}
public int getReceiverId()
{
	return receiverId;
}
public void setReceiverId(int receiverId)
{
	this.receiverId = receiverId;
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
