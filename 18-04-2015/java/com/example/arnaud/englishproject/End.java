package com.example.arnaud.englishproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class End extends Activity
{
	private TextView text;
	private Button button;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end);
		text= (TextView) findViewById(R.id.score);
		button = (Button) findViewById(R.id.again);
		text.setText("Your score : "+getIntent().getStringExtra("point"));
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_button);
        button.startAnimation(animation);
		button.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
                Intent intent = new Intent(End.this, Subject.class);
                intent.putExtra("type","quizz");
                startActivity(intent);
                finish();
			}
		});
	}
}
