package com.example.arnaud.englishproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class End extends Activity {
    private TextView score, bestScore;
    private Button againBtn, saveBtn;
    private String bestPlayer="";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end);

        score = (TextView) findViewById(R.id.score);
        score.setText("Your score : " + getIntent().getStringExtra("point"));

        //Turn it invisible
        bestScore = (TextView) findViewById(R.id.bestScore);
        bestScore.setVisibility(View.INVISIBLE);
        //bestScore.setText("Record : ");
        //Lecture du fichier de score


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_button);

        saveBtn = (Button) findViewById(R.id.save);
        saveBtn.startAnimation(animation);
        saveBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save data
                //parametre : getIntent().getStringExtra("point"));
                //saveScore("boutchou-"+getIntent().getStringExtra("point"));
                Log.d("END", "score a sauvegarder");
                notImplemented("Saving scores");

            }
        });

        againBtn = (Button) findViewById(R.id.again);
        againBtn.startAnimation(animation);
        againBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(End.this, Subject.class);
                intent.putExtra("type", "quizz");
                startActivity(intent);
                finish();
            }
        });

    }

    public void notImplemented(String msg){
        Toast.makeText(this, msg+",\nnot implemented yet", Toast.LENGTH_SHORT).show();
    }


}