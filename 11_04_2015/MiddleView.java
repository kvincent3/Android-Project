package com.example.englishproject;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MiddleView 
{
	private ViewUserPanel viewuserpanel;
	private ViewMap viewmap;
	private Button check;
	
	public MiddleView (Activity a,ViewUserPanel v,ViewMap vi)
	{
		this.viewuserpanel=v;
		this.viewmap=vi;
        this.check = (Button) a.findViewById(R.id.check);
        this.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue)
            {
                Log.d("Check ", "Checkem");
                if (viewuserpanel.check_validity())
                {
                	viewuserpanel.refreshPage();
                	viewmap.Refresh();
                }
            }
        });
	}
}
