package com.example.arnaud.englishproject;



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
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class ViewUserPanel
{
    private Context context;
    private ArrayList<Question> modQuestion;
    private int current;

    private View mainView;
    private ViewSwitcher  myViewToSwitch;
    private View ViewWithQuestion;
    private TextView questionTextView, indicationTextView;

    private Animation slide_in_left;
    private Animation slide_out_right;

    private Button hint, quizz, check;
    private ArrayList<RadioButton> radioButtonlist = new ArrayList<RadioButton> ();


    public ViewUserPanel(View v, Context c, ArrayList<Question> modQuestion)
    {
        this.mainView = v;
        this.context = c;
        this.modQuestion = modQuestion;
        this.current = 0;

        if (this.modQuestion == null) {
            Log.d("Modele ", "alloc failed");
        }


        this.insertIntoMainLayout();

        this.myViewToSwitch = (ViewSwitcher) this.mainView.findViewById(R.id.viewSwitcher1);
        if (this.myViewToSwitch == null){
            Log.d("VIEW : ", "FAILED");
        }

        this.slide_in_left = AnimationUtils.loadAnimation(this.context, android.R.anim.slide_in_left);
        this.slide_out_right = AnimationUtils.loadAnimation(this.context, android.R.anim.slide_out_right);

        this.myViewToSwitch.setInAnimation(this.slide_in_left);
        this.myViewToSwitch.setOutAnimation(this.slide_out_right);

        this.hint = (Button) v.findViewById(R.id.indication);
        this.hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue)
            {
                Log.d("Indication ", "Checkem");
                myViewToSwitch.showNext();
            }
        });


        this.quizz = (Button) this.mainView.findViewById(R.id.quizz);
        this.quizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue)
            {
                myViewToSwitch.showPrevious();
            }
        });

        this.check = (Button) this.mainView.findViewById(R.id.check);
        this.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue)
            {
                Log.d("Check ", "Checkem");
                check_validity();
            }
        });


        //Load content to the specific elements of the view
        this.initializeRadio();
        this.loadButton();
        this.loadIndication();


    }

    public void insertIntoMainLayout()
    {
        //Inflate the Hidden Layout Information View
        this.indicationTextView=new TextView(this.context);
        this.loadTitleQuestion();

        FrameLayout myFrame = (FrameLayout) mainView.findViewById(R.id.myframe);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        ViewWithQuestion = inflater.inflate(R.layout.justquestion, myFrame, false);
        myFrame.addView(ViewWithQuestion);

    }

    public void initializeRadio()
    {
        RadioButton r0 = (RadioButton) mainView.findViewById(R.id.radio0);
        RadioButton r1 = (RadioButton) mainView.findViewById(R.id.radio1);
        RadioButton r2 = (RadioButton) mainView.findViewById(R.id.radio2);
        RadioButton r3 = (RadioButton) mainView.findViewById(R.id.radio3);
        radioButtonlist.add(r0);
        radioButtonlist.add(r1);
        radioButtonlist.add(r2);
        radioButtonlist.add(r3);
    }

    public void loadTitleQuestion(){
        this.questionTextView = (TextView) this.mainView.findViewById(R.id.textView1);
        this.questionTextView.setText(this.modQuestion.get(current).getQuestion());
    }

    public void loadButton(){
        for(int i=0 ; i<this.modQuestion.get(current).getChoices().size();i++)
        {
            radioButtonlist.get(i).setText(this.modQuestion.get(current).getChoices().get(i));
        }
    }

    public void loadIndication(){

        this.indicationTextView= (TextView) this.mainView.findViewById(R.id.MyindicationText);
        this.indicationTextView.setText(this.modQuestion.get(current).getHints().get(0));
    }

    public void check_validity(){

        String answer = this.modQuestion.get(current).getCorrect();
        int i = 0;
        Boolean correct = false;

        while(i<this.radioButtonlist.size() && !correct){

            if (this.radioButtonlist.get(i).isChecked()){
                //System.out.println("radio button activated : "+i+" "+ " "+this.radioButtonlist.get(i).getText());
                correct = this.radioButtonlist.get(i).getText().equals(answer);
            }
            i++;
        }

        //this.indicationTextView = new TextView(this.context);
        if (correct){

            if (this.current++ < this.modQuestion.size()){
                this.refreshPage();
            }else{
                Log.d("VIEW", "A court de question : "+this.current+" VS "+this.modQuestion.size());
            }

            Toast.makeText(this.context,
                    "Correct ! ", Toast.LENGTH_SHORT).show();
            this.modQuestion.get(current).setPassed(true);
            //switch to the new question
        }else{
            Toast.makeText(this.context,
                    "Wrong ! ", Toast.LENGTH_SHORT).show();
        }
    }

    public void refreshPage(){
        this.loadTitleQuestion();
        this.loadButton();
        this.loadIndication();
        synchronized (this){
            this.notify();
        }

    }

}
