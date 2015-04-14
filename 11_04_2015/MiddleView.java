package com.example.arnaud.englishproject;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MiddleView 
{
	private ViewUserPanel viewUserPanel;
	private ViewMap viewMap;
	private Button check;
	
	public MiddleView (Activity a,ViewUserPanel v,ViewMap vi)
	{
		this.viewUserPanel = v;
		this.viewMap=vi;
        this.check = (Button) a.findViewById(R.id.check);
        this.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue)
            {
                Log.d("Check ", "Checkem");
                if (viewUserPanel.check_validity())
                {
                    viewUserPanel.refreshPage();
                    viewMap.Refresh();
                }
            }
        });
	}
}
