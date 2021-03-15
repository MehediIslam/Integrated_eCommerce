package model;
public class Categorie 
{
	
	private int catId;
	private String catName,description;
	
	
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	public String getDescription()
	{
		return description;
		
	}
	public void setDescription(String description)
	{
		this.description = description;
		
	}

}
