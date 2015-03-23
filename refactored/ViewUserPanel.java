package com.example.englishproject;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class ViewUserPanel 
{
	Context context;
	Question q; // Notre modele pour generer la vue
	
	View view;
	ViewSwitcher  myViewToSwitch;
	View ViewWithQuestion;
	
	Animation slide_in_left;
	Animation slide_out_right;
	
	Button hint, quizz, check;
    
	ArrayList<RadioButton> radioButtonlist = new ArrayList<RadioButton> ();
	TextView indicationTextView;
	
	public ViewUserPanel(View v, Context c, Question q)
	{
		 this.view=v;
		 this.context=c;
		 this.q = q;
		 
		 this.insertIntoMainLayout();
		 
		 this.myViewToSwitch = (ViewSwitcher) v.findViewById(R.id.viewSwitcher1);
         slide_in_left = AnimationUtils.loadAnimation(c,android.R.anim.slide_in_left);
         slide_out_right = AnimationUtils.loadAnimation(c,android.R.anim.slide_out_right);
         
         myViewToSwitch.setInAnimation(slide_in_left);
         myViewToSwitch.setOutAnimation(slide_out_right);
         
 		 hint = (Button) v.findViewById(R.id.indication);
 		 hint.setOnClickListener(new View.OnClickListener() {
		      @Override
		      public void onClick(View vue) 
		      {
		    	  Log.d("Indication ", "Checkem");
		    	  myViewToSwitch.showNext();
		      }
		 });
		 
 		 
 		 quizz = (Button) this.view.findViewById(R.id.quizz);
         quizz.setOnClickListener(new View.OnClickListener() {
		      @Override
		      public void onClick(View vue) 
		      {
		    	  myViewToSwitch.showPrevious();
		      }
		 });
         
         check = (Button) this.view.findViewById(R.id.check);
         check.setOnClickListener(new View.OnClickListener() {
		      @Override
		      public void onClick(View vue) 
		      {
		    	  Log.d("Check ", "Checkem");
		    	  if (check_validity()) {
		    		  Log.d("Validation : ", "Correct !!!!");		    		  
		    	  }else{
		    		  Log.d("Validation : ", "FAILED !!!!");
		    	  }		    	  
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
	
	public void InitializeRadio(ModelUserPanel a)
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
	
	public boolean check_validity(){
		
		String correct = this.q.getCorrect();
		int i = 0;
		
		while(i<this.radioButtonlist.size()){
			
			//System.out.println("valeurs à tester : ");
			//System.out.println("radio button : "+this.radioButtonlist.get(i).getText());
			//System.out.println("correct : "+correct);			
			if (this.radioButtonlist.get(i).isChecked()){				
				//System.out.println("radio button activated : "+i+" "+ " "+this.radioButtonlist.get(i).getText());
				if (this.radioButtonlist.get(i).getText().equals(correct) ){
					return true;					
				}else{
					return false;
				}				
			}			
			i++;
		}		
		return false;
	}

}
