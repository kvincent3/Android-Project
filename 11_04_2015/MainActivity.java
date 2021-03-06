package com.example.arnaud.englishproject;


import android.app.Activity;
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

        //Set up the model appropriate to the specific typesend by home.java
        this.middleman = new Middleman(this, "touch.txt", "mapdata.txt");
        //this.middleman = new Middleman(this, "history.txt", "mapdata.txt");
        //Log.d("model", ""+this.middleman.getModQuestions().size()+"\n"+this.middleman.getModMap().size()+"\n"+this.middleman.getModBar().getDelai());


        ViewMap relativeMap = new ViewMap(this, this.middleman);

        //The header also refers to the same Model : upBar, which only needs to be refreshed from time to time
        ViewBarTop header = new ViewBarTop(this.view, this, this.middleman.getModBar());

        //The userView refers to a specific question only for now
        ViewUserPanel userView = new ViewUserPanel(this.view, this, this.middleman.getModQuestions());


        //refer to the middle view;
        //Only refering User and map for now
        MiddleView midView = new MiddleView(this, userView, relativeMap);






    }
}

