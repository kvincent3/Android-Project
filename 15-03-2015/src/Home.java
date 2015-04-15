package com.example.englishproject;



        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.Button;

public class Home extends Activity implements android.view.View.OnClickListener
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        Button but = (Button) findViewById(R.id.quizzbutton);
        Button but2 = (Button) findViewById(R.id.history);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_button);
        but.startAnimation(animation);
        but2.startAnimation(animation);
        but.setOnClickListener(this);
        but2.setOnClickListener(this);

    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.quizzbutton :
                Intent intent = new Intent(Home.this, Subject.class);
                intent.putExtra("type","quizz");
                startActivity(intent);
                break;
            case R.id.history : 
                Intent intent2 = new Intent(Home.this, Subject.class);
                intent2.putExtra("type","history");
                startActivity(intent2);
                break; 


        }

    }
}
