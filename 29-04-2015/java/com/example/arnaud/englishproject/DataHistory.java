package com.example.arnaud.englishproject;

import java.util.ArrayList;

public class DataHistory
{
	MarkerInstance marker;
	String story;
	public DataHistory(MarkerInstance m, String story)
	{
		this.marker = m;
        this.story = story;
	}

    public MarkerInstance getMarker() {
        return marker;
    }

    public void setMarker(MarkerInstance marker) {
        this.marker = marker;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
