package model;

public class Item2<V> implements Comparable<Item2>
{
	private Integer value;
	private String description;

	/**
	 *  Create an Item object
	 *
	 *  @param value an Object containing data used by the application
	 *  @param description the text to be displayed by a renderer
	 */
	public Item2(int value, String description)
	{
		this.value = value;
		this.description = description;
	}

	/**
	 *  Get the Object containing application data
	 *
	 *  @returns the application data
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 *  Get the description of the value data
	 *
	 *  @returns the description to be displayed by a renderer
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 *  Implement the natural order for this class using the
	 *  Description property
	 *
	 *  @param item the other Item object to be used in the comparison
	 */
	public int compareTo(Item2 item)
	{
		return getDescription().compareTo(item.getDescription());
	}

	/**
	 *  The Value property will be used to check for equality
	 */
	@Override
	public boolean equals(Object object)
	{
		Item2 item = (Item2)object;
		return value.equals(item.getValue());
	}

	/**
	 *  The Value property will be used to determine the hashCode
	 */
	@Override
	public int hashCode()
	{
		return value.hashCode();
	}

	/**
	 *	The Description property will double as the toString representation.
	 *
	 *  @return the description
	 */
	@Override
	public String toString()
	{
		return description;
	}
}