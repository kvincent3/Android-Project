package com.example.englishproject;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Subject extends Activity implements OnClickListener
{
    private String type;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject);
        Intent i= getIntent();
        type=i.getStringExtra("type");//on dit si le type est quizz ou history
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_button);
        Button hist = (Button) findViewById(R.id.histoire);
        Button war = (Button) findViewById(R.id.guerre);
        Button monu = (Button) findViewById(R.id.monument);
        Button mus = (Button) findViewById(R.id.musique);
        hist.startAnimation(animation);
        war.startAnimation(animation);
        monu.startAnimation(animation);
        mus.startAnimation(animation);
        
        hist.setOnClickListener(this);
        war.setOnClickListener(this);
        monu.setOnClickListener(this);
        mus.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.histoire :
                if (type.equals("quizz"))
                {
                    Intent intent = new Intent(Subject.this, MainActivity.class);
                    intent.putExtra("subject","history");
                    startActivity(intent);
                    this.finish();
                }
                else if (type.equals("history"))
                {
                    //implémenté l'activité pour le mode history
                }
                break;
            case R.id.guerre :
                if (type.equals("quizz"))
                {
                    Intent intent = new Intent(Subject.this, MainActivity.class);
                    intent.putExtra("subject","war");
                    startActivity(intent);
                    this.finish();
                }
                else if (type.equals("history"))
                {
                    //implémenté l'activité pour le mode history
                }
                break;
            case R.id.monument :
                if (type.equals("quizz"))
                {
                    Intent intent = new Intent(Subject.this, MainActivity.class);
                    intent.putExtra("subject","monuments");
                    startActivity(intent);
                    this.finish();
                }
                else if (type.equals("history"))
                {
                    //implémenté l'activité pour le mode history
                }
                break;
            case R.id.musique:
                if (type.equals("quizz"))
                {
                    Intent intent = new Intent(Subject.this, MainActivity.class);
                    intent.putExtra("subject","music");
                    startActivity(intent);
                    this.finish();
                }
                else if (type.equals("history"))
                {
                    //implémenté l'activité pour le mode history
                }
                break;



        }

    }
}
