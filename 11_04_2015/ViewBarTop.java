package com.example.arnaud.englishproject;



import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewBarTop
{

    private Animation animation;
    private LinearLayout layout;
    private TextView delai, points;

    private ModelBar model;
    private View mainView;

    public ViewBarTop(View v,Context context,ModelBar m)
    {
        this.mainView=v;
        this.model=m;

        this.layout= (LinearLayout) v.findViewById(R.id.myLinear);
        this.animation = AnimationUtils.loadAnimation(context, R.anim.bartopanimation);
        this.layout.startAnimation(this.animation);

        this.initialize();
    }

    public void initialize()
    {
        this.delai = (TextView) mainView.findViewById(R.id.delay);
        this.delai.setText(""+this.model.getDelai());
        this.points = (TextView) mainView.findViewById(R.id.points);
        this.points.setText(""+this.model.getPoints());
    }

    public void refresh(){

    }

    public TextView getDelai()
    {
        return this.delai;
    }


    public TextView getPoints()
    {
        return this.points;
    }

    public void setDelai(TextView newdel)
    {
        this.delai=newdel;
    }



    public void setPoints(TextView newPoints)
    {
        this.points=newPoints;
    }

}
