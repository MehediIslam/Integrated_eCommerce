package model;
import java.util.Date;
public class CustomerDiscount 
{
	
	private int id,userId;
	private double discount;
	private Date startDate,endDate;
	
	public int getId()
	{
	return id;	
	}
	public void setId(int id)
	{
		this.id = id;
		
	}
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
		
	}
	public double getDiscount()
	{	
		return discount;
	}
	public void setDiscount(double discount)
	{
		this.discount = discount;	
	}
	public Date getStartDate()
	{
		return startDate;
	}
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
	public Date getEndDate()
	{
		return endDate;
	}
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}	
}