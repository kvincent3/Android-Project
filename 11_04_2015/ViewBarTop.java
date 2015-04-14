package com.example.englishproject;



import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewBarTop
{
    LinearLayout layout;
    Animation animation;
    TextView delai;
    TextView question;
    TextView points;
    ModelBar model;
    View v;
    public ViewBarTop(View v,Context context,ModelBar m)
    {
        this.v=v;
        this.model=m;

        this.layout= (LinearLayout) v.findViewById(R.id.myLinear);
        this.animation = AnimationUtils.loadAnimation(context, R.anim.bartopanimation);
        this.layout.startAnimation(this.animation);

    }

    public void InitializeTextView()
    {
        this.delai= (TextView) v.findViewById(R.id.delai);
        this.delai.setText(""+this.model.getDelai());
        this.question= (TextView) v.findViewById(R.id.question);
        this.question.setText(""+this.model.getQuestion());
        this.points= (TextView) v.findViewById(R.id.points);
        this.points.setText(""+this.model.getPoints());
    }

    public TextView getDelai()
    {
        return this.delai;
    }

    public TextView getQuestion()
    {
        return this.question;
    }

    public TextView getPoints()
    {
        return this.points;
    }

    public void setDelai(TextView newdel)
    {
        this.delai=newdel;
    }

    public void setQuestion(TextView newquest)
    {
        this.question=newquest;
    }

    public void setPoints(TextView newPoints)
    {
        this.points=newPoints;
    }

}
