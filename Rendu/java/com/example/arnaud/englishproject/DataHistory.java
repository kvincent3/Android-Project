package com.example.arnaud.englishproject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataHistory
{
	MarkerInstance marker;
	ArrayList<String> story = new ArrayList<String>();
	public DataHistory(MarkerInstance m, ArrayList<String> story)
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

    public ArrayList<String> getStory() {
        return story;
    }

}
