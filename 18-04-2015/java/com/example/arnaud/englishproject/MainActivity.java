package com.example.arnaud.englishproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {
    private Middleman middleman;
    private View view;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.view = getWindow().getDecorView().findViewById(android.R.id.content);
        setContentView(R.layout.activity_main);


        Intent i= getIntent();
        String subject=i.getStringExtra("subject");

        Boolean toTouch = false;
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
            toTouch = true;
        }
        else if (subject.equals("music"))
        {
            this.middleman = new Middleman(this, "music.txt", "mapdata.txt");
        }

        //this.middleman = new Middleman(this, "touch.txt", "mapdata.txt");
        this.middleman.setMaxNumberQuestion(10);



        //nb : toTouch = true si on appel un fichier de quizz et de map
        MiddleView midView = new MiddleView(this, this.view, this.middleman, toTouch);


    }


}


