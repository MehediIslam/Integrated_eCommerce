package model;

public class Profile 
{
	private int profileId;
	private int userId;
	private String name;
	private String phone;
	private String email;
	private String address;
	private int creatorId;
	
	
	public int getProfileId() 
	{
		return profileId;
	}
	public void setProfileId(int profileId) 
	{
		this.profileId = profileId;
	}
	public int getUserId() 
	{
		return userId;
	}
	public void setUserId(int userId) 
	{
		this.userId = userId;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getPhone() 
	{
		return phone;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getAddress() 
	{
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	public int getCreatorId() 
	{
		return creatorId;
	}
	public void setCreatorId(int creatorId) 
	{
		this.creatorId = creatorId;
	}
	

}
