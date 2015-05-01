package com.example.arnaud.englishproject;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment; 

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class History extends Activity
{
	private GoogleMap map;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        Log.d("test","on y est");
        Middleman middleman = new Middleman(this, "datahistory.txt");
        new MiddleView(this,middleman);
    }
}
