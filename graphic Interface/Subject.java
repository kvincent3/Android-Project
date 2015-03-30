package com.example.englishproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Subject extends Activity implements OnClickListener
{
	private String type;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject);
		Intent i= getIntent();
	    type=i.getStringExtra("type");//on dit si le type est quizz ou history
		ImageView hist = (ImageView) findViewById(R.id.imageHistory);
		ImageView war = (ImageView) findViewById(R.id.imagewar);
		ImageView monu = (ImageView) findViewById(R.id.Imagemonum);
		ImageView mus = (ImageView) findViewById(R.id.Imagemusic);
		hist.setOnClickListener(this);
		war.setOnClickListener(this);
		monu.setOnClickListener(this);
		mus.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
		   case R.id.imageHistory :
			   if (type.equals("quizz"))
			   {
				  Intent intent = new Intent(Subject.this, MainActivity.class);
				  intent.putExtra("subject","history");
				  startActivity(intent);
			   }
			   else if (type.equals("history"))
			   {
                  //implémenté l'activité pour le mode history			   
			   }
		   break;
		   case R.id.imagewar :
			   if (type.equals("quizz"))
			   {
				  Intent intent = new Intent(Subject.this, MainActivity.class);
				  intent.putExtra("subject","war");
				  startActivity(intent);
			   }
			   else if (type.equals("history"))
			   {
                  //implémenté l'activité pour le mode history			   
			   }
		   break;
		   case R.id.Imagemonum :
			   if (type.equals("quizz"))
			   {
				  Intent intent = new Intent(Subject.this, MainActivity.class);
				  intent.putExtra("subject","monuments");
				  startActivity(intent);
			   }
			   else if (type.equals("history"))
			   {
                  //implémenté l'activité pour le mode history			   
			   }
		   break;
		   case R.id.Imagemusic :
			   if (type.equals("quizz"))
			   {
				  Intent intent = new Intent(Subject.this, MainActivity.class);
				  intent.putExtra("subject","music");
				  startActivity(intent);
			   }
			   else if (type.equals("history"))
			   {
                  //implémenté l'activité pour le mode history			   
			   }
		   break;
		   
		   
		   
		}
		
	}
}
