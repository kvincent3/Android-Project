package com.example.englishproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;

public class ViewIndicationMap {

	View viewWithMap;
	RelativeLayout relative;
	View v;
	Context c;
	
	public ViewIndicationMap(View v, Context c){
		this.v=v;
		this.c=c;
		this.relative= (RelativeLayout) v.findViewById(R.id.myRelative);
		this.insertIntoMainLayout();
	}

	private void insertIntoMainLayout()	{

		 if (this.relative==null)
		 {
               FrameLayout myFrame = (FrameLayout) this.v.findViewById(R.id.myframe);
               LayoutInflater inflater = (LayoutInflater) this.c.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
               viewWithMap = inflater.inflate(R.layout.indicationmap, myFrame, false);
               myFrame.addView(viewWithMap);
		 }
	}
}
