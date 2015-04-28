package com.example.englishproject;

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

        bestScore = (TextView) findViewById(R.id.bestScore);
        bestScore.setText("Record : ");


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


        //Lecture du fichier de score


    }

    /*
    public void createScoreFile(String entry) throws FileNotFoundException {
        String FILENAME = "score";
        try {
            FileOutputStream fos = openFileOutput(FILENAME, this.MODE_PRIVATE);
            fos.write(entry.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean readScoreFile() {

        FileInputStream fin;
        String FILENAME = "score";

        try {

            fin = openFileInput(FILENAME);
            byte fileContent[] = new byte[(int)FILENAME.length()];

            fin.read(fileContent);



            if (fileContent.length > 1){
                String s = new String(fileContent);

                //La première ligne contient le nom du meilleur score
                Log.d("Read", "s : "+s.split("\n"));
                if ( (this.bestPlayer = s.split("\n")[0]) != null){
                    this.bestScore.append(this.bestPlayer.split("-")[0]+"\n"+this.bestPlayer.split("-")[1]);
                }else{
                    this.bestScore.append(" (-ç-) ");
                }

            }else{
                this.bestScore.append(" (-è-) ");
            }

            fin.close();


        } catch (IOException e) {
            e.printStackTrace();
            return  false;
        }

        return true;

    }

    public void saveScore(String entry){

        FileInputStream fin;
        FileOutputStream fos;

        Boolean inserted = false;
        String FILENAME = "score";
        String content = "";

        try {

            fin = openFileInput(FILENAME);
            byte fileContent[] = new byte[(int)FILENAME.length()];

            fin.read(fileContent);
            String s = new String(fileContent);

            Log.d("READ", "content : "+ content);
            for (String line : s.split("\n")){
                Log.d("READ", "line : "+line);

                if ( Integer.parseInt(line.split("-")[1]) < Integer.parseInt(entry.split("-")[1])
                        && !inserted){
                    content = content + "\n" + entry;
                    inserted = true;
                }else{
                    content = content + "\n" + line;
                }
            }

            fos = openFileOutput(FILENAME, this.MODE_PRIVATE);
            fos.flush();
            fos.write(content.getBytes());
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */


}