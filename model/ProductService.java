package model;

public class ProductService 
{
	private int id;
	private int serviceId;
	private int productId;
	private String problemDescription;
	private double repairCost;
	private String status;
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public int getServiceId() 
	{
		return serviceId;
	}
	public void setServiceId(int serviceId) 
	{
		this.serviceId = serviceId;
	}
	public int getProductId() 
	{
		return productId;
	}
	public void setProductId(int productId) 
	{
		this.productId = productId;
	}
	public String getProblemDescription() 
	{
		return problemDescription;
	}
	public void setProblemDescription(String problemDescription) 
	{
		this.problemDescription = problemDescription;
	}
	public double getRepairCost() 
	{
		return repairCost;
	}
	public void setRepairCost(double repairCost) 
	{
		this.repairCost = repairCost;
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
