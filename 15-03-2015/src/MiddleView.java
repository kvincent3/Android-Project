package com.example.englishproject;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MiddleView 
{
	private ViewUserPanel viewUserPanel;
	private ViewMap viewMap;
	private ViewBarTop viewBarTop;
	private Button check;
	
	public MiddleView (Activity a,ViewUserPanel v,ViewMap vi,ViewBarTop view)
	{
		this.viewUserPanel = v;
		this.viewBarTop=view;
		this.viewMap=vi;
        this.check = (Button) a.findViewById(R.id.check);
        this.check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vue)
            {
                Log.d("Check ", "Checkem");
                if (viewUserPanel.check_validity())
                {
                	
                    viewUserPanel.refreshUser();
                    viewMap.refreshMap();
                    viewBarTop.refresh(true);
                }
                else
                {
                	viewBarTop.refresh(false);
                }
            }
        });
	}
}
