package com.example.englishproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class ViewReponseAndIndication 
{
	ViewSwitcher  myViewToSwitch;
	Animation slide_in_left;
	Animation slide_out_right;
	Button indication;
    View ViewWithQuestion;
	Button quizz;
	View view;
	Context context;
	ArrayList<RadioButton> radioButtonlist =new ArrayList<RadioButton> ();
	TextView indicationTextView;
	public ViewReponseAndIndication(View v,Context c)
	{
		 this.view=v;
		 this.context=c;
		 this.insertIntoMainLayout();
		 this.myViewToSwitch = (ViewSwitcher) v.findViewById(R.id.viewSwitcher1);
         slide_in_left = AnimationUtils.loadAnimation(c,android.R.anim.slide_in_left);
         slide_out_right = AnimationUtils.loadAnimation(c,android.R.anim.slide_out_right);
         myViewToSwitch.setInAnimation(slide_in_left);
         myViewToSwitch.setOutAnimation(slide_out_right);
 		 indication = (Button) v.findViewById(R.id.indication);
		 quizz = (Button) v.findViewById(R.id.quizz);
         indication.setOnClickListener(new View.OnClickListener() {
		      @Override
		      public void onClick(View vue) 
		      {
		    	  myViewToSwitch.showNext();

		      }
		    });
        quizz.setOnClickListener(new View.OnClickListener() {
		      @Override
		      public void onClick(View vue) 
		      {
		    	  myViewToSwitch.showPrevious();

		      }
		    });
	}
	
	public void insertIntoMainLayout()
	{
	    this.myViewToSwitch = (ViewSwitcher) view.findViewById(R.id.viewSwitcher1);
        if(myViewToSwitch == null)
        {
            //Inflate the Hidden Layout Information View
            FrameLayout myFrame = (FrameLayout) view.findViewById(R.id.myframe);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            ViewWithQuestion = inflater.inflate(R.layout.justquestion, myFrame, false);
            myFrame.addView(ViewWithQuestion);
         }
        
	}
	
	public void InitializeRadio(ModelReponseAndIndication a)
	{
		RadioButton r0 = (RadioButton) view.findViewById(R.id.radio0);
		RadioButton r1 = (RadioButton) view.findViewById(R.id.radio1);
		RadioButton r2 = (RadioButton) view.findViewById(R.id.radio2);
		RadioButton r3 = (RadioButton) view.findViewById(R.id.radio3);
		radioButtonlist.add(r0);
		radioButtonlist.add(r1);
		radioButtonlist.add(r2);
		radioButtonlist.add(r3);
		for(int i=0;i<a.getReponse().size();i++)
		{
			radioButtonlist.get(i).setText(a.getReponse().get(i));
		}
		for (int i=a.getReponse().size();i<4;i++)
		{
			radioButtonlist.get(i).setVisibility(View.INVISIBLE);
		}
		this.indicationTextView=new TextView(this.context);
		this.indicationTextView= (TextView) this.view.findViewById(R.id.MyindicationText);
		this.indicationTextView.setText(a.getIndication());
	}

}
