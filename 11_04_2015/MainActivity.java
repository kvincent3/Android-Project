package com.example.arnaud.englishproject;

import com.google.android.gms.maps.GoogleMap;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;



public class MainActivity extends Activity {
    private Middleman middleman;
    private GoogleMap map;
    View view;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.view = getWindow().getDecorView().findViewById(android.R.id.content);
        setContentView(R.layout.activity_main);

        //Set up the model
        this.middleman = new Middleman(this, "history.txt", "mapdata.txt");
        Log.d("model", ""+this.middleman.getModQuestions().size()+"\n"+this.middleman.getModMap().size()+"\n"+this.middleman.getModBar().getDelai());

        //UserPanel is useless cause it only replicates data of the questions Model
        //ModelUserPanel userPanel;

        //ViewMap is related to the location of the question, which links to the MapsModel
        //So it refers only one particular question of the model Map
        //Instanciate a location based on the place of the question at index 0
        Location loc = this.middleman.giveMeMap( this.middleman.giveMeQuestion(0).getPlace() );
        ViewMap relativeMap = new ViewMap(this, map, loc);

        //The header also refers to the same Model : upBar, which only needs to be refreshed from time to time
        ViewBarTop header = new ViewBarTop(this.view, this, this.middleman.getModBar());
        header.InitializeTextView();

        //The userView refers to a specific question only for now
        ViewUserPanel userView = new ViewUserPanel(this.view, this, this.middleman.getModQuestions());


        /*
        //set up the activity
        courant = mytestlearn.GiveMeDataGame(0);

        //generate an instance of current hints and answers in order to display them
        ModelUserPanel userPanel = new ModelUserPanel(courant.getHints().get(0), courant.getChoices());


        ViewBarTop header = new ViewBarTop(this.view, this, upBar);
        header.InitializeTextView();

        if (courant.getChoices().size() > 1) {
            ViewUserPanel userView = new ViewUserPanel(this.view, this, courant); //On passe le modele � al vue : la question
            userView.InitializeRadio(userPanel);
        }

        */





    }
}
