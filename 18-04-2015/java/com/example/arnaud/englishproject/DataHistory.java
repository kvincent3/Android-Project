package com.example.englishproject;

public class DataHistory 
{
	Location location;
	String story;
	public DataHistory()
	{
		this.location =new Location();
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location l) {
		this.location = l;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
}
