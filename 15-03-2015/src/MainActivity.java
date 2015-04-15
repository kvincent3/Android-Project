package com.example.englishproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;



public class MainActivity extends Activity {
	private Middleman middleman;
	private View view;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.view = getWindow().getDecorView().findViewById(android.R.id.content);
		setContentView(R.layout.activity_main);
		Intent i= getIntent();
		String subject=i.getStringExtra("subject");
		//Set up the model appropriate to the specific typesend by home.java
		if (subject.equals("history"))
		{
			this.middleman = new Middleman(this, "history.txt", "mapdata.txt");
		}
		else if (subject.equals("war"))
		{
			this.middleman = new Middleman(this, "war.txt", "mapdata.txt");
		}
		else if (subject.equals("monuments"))
		{
			this.middleman = new Middleman(this, "monuments.txt", "mapdata.txt");
		}
		else if (subject.equals("music"))
		{
			this.middleman = new Middleman(this, "music.txt", "mapdata.txt");
		}
		ViewMap relativeMap = new ViewMap(this, this.middleman);

		//The header also refers to the same Model : upBar, which only needs to be refreshed from time to time
		ViewBarTop header = new ViewBarTop(this.view, this, this.middleman.getModBar());

		//The userView refers to a specific question only for now
		ViewUserPanel userView = new ViewUserPanel(this.view, this, this.middleman.getModQuestions());


		//refer to the middle view;
		//Only refering User and map for now
		MiddleView midView = new MiddleView(this, userView, relativeMap,header);

	}
}

