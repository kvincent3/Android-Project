package com.example.englishproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewBarTop
{

    private Animation animation;
    private LinearLayout layout;
    private TextView delai, textPoints;
    private ModelBar model;
    private View mainView;
    private int timer;
    private long millis=32000;
    private Handler timerHandler = new Handler();
    private boolean goOn=true;
    private Context context;
    private TextView question;
    private Middleman middleman;

    private Runnable timerRunnable = new Runnable()
    {

        @Override
        public void run()
        {
            if(isGoOn())
            {
                setMillis(getMillis()-1000);
                int seconds = (int) (getMillis() / 1000);
                setTimer(seconds % 60);
                setTimer(getTimer()-1);
                delai.setText(String.format("%02d", getTimer()));
                if (getTimer()==0)
                {
                    this.stop();
                }
                timerHandler.postDelayed(this, 1000);

            }
        }

        public void stop()
        {
            setGoOn(false);
            timerHandler.removeCallbacks(this);
        }
    };





    public ViewBarTop(View v,Context context,Middleman middleman)
    {
        this.mainView=v;
        this.context=context;
        this.middleman=middleman;
        this.model=this.getMiddleman().getModBar();
        this.layout= (LinearLayout) v.findViewById(R.id.myLinear);
        this.animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.bartopanimation);
        this.layout.startAnimation(this.animation);
        timerHandler.postDelayed(timerRunnable, 0);
        this.initialize();
    }



    public void initialize()
    {
        this.delai = (TextView) mainView.findViewById(R.id.delay);
        this.delai.setText(""+this.model.getDelai());
        this.textPoints = (TextView) mainView.findViewById(R.id.points);
        this.textPoints.setText(""+this.model.getPoints());
        this.setQuestion((TextView) mainView.findViewById(R.id.question));
        this.getMiddleman().setQuestionNumber(1);
        this.getQuestion().setText("Question "+this.getMiddleman().getQuestionNumber());

    }

    public void refresh(boolean answer)
    {
        if (answer)
        {
            setMillis(32000);
            this.getModel().updatesGoodAnswer(this.getTimer());
            this.getTextPoints().setText(""+this.getModel().getPoints());
            this.animation = AnimationUtils.loadAnimation(context, R.anim.animgoodpoints);
            this.getTextPoints().startAnimation(this.animation);
            int question = this.getMiddleman().getQuestionNumber();
            this.getMiddleman().setQuestionNumber(question+1);
            if (question!=this.getMiddleman().getMaxNumberQuestion()+1)
            {
                this.getQuestion().setText("Question "+this.getMiddleman().getQuestionNumber());
                if (getTimer()!=0)
                {
                    timerHandler.removeCallbacks(timerRunnable);
                }
                this.setGoOn(true);
                timerHandler.postDelayed(timerRunnable, 0);
            }
            else
            {
                Log.d("question", ""+question);
                Log.d("questionmax", ""+this.getMiddleman().getMaxNumberQuestion());
                Intent intent = new Intent(this.getContext(), End.class);
                intent.putExtra("point",""+this.getModel().getPoints());
                this.getContext().startActivity(intent);
                ((Activity) this.getContext()).finish();
            }
        }
        else
        {
            this.getModel().updatesBadAnswer(this.getTimer());
            this.getTextPoints().setText(""+this.getModel().getPoints());
            this.animation = AnimationUtils.loadAnimation(context, R.anim.animbadpoints);
            this.getTextPoints().startAnimation(this.animation);
        }
    }

    public TextView getDelai()
    {
        return this.delai;
    }


    public TextView getTextPoints()
    {
        return this.textPoints;
    }

    public void setDelai(TextView newdel)
    {
        this.delai=newdel;
    }


    public long getMillis() {
        return millis;
    }



    public void setMillis(long millis) {
        this.millis = millis;
    }
    public void setPoints(TextView newPoints)
    {
        this.textPoints=newPoints;
    }



    public int getTimer() {
        return timer;
    }


    public void setTimer(int timer) {
        this.timer = timer;
    }

    public boolean isGoOn() {
        return goOn;
    }


    public void setGoOn(boolean goOn) {
        this.goOn = goOn;
    }

    public ModelBar getModel() {
        return model;
    }

    public void setModel(ModelBar model) {
        this.model = model;
    }


    public Context getContext() {
        return context;
    }



    public void setContext(Context context) {
        this.context = context;
    }



    public TextView getQuestion() {
        return question;
    }



    public void setQuestion(TextView question) {
        this.question = question;
    }



    public Middleman getMiddleman() {
        return middleman;
    }



    public void setMiddleman(Middleman middleman) {
        this.middleman = middleman;
    }




}
