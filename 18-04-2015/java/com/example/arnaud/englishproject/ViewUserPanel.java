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
import android.widget.ViewSwitcher;

public class ViewUserPanel
{
    private Context context;

    private View mainView;
    private ViewSwitcher  mainSwitch, quizzSwitch;
    private View ViewWithQuestion;
    private TextView questionTextView, indicationTextView;

    private Animation slide_in_left;
    private Animation slide_out_right;

    private Button hint, quizz;
    private ArrayList<RadioButton> radioButtonlist = new ArrayList<RadioButton> ();
    private boolean setOnQuizz;


    public ViewUserPanel(View v, Context c, Question question, Boolean mapToTouch)
    {
        this.mainView = v;
        this.context = c;

        this.insertIntoMainLayout(question);

        this.slide_in_left = AnimationUtils.loadAnimation(this.context, android.R.anim.slide_in_left);
        this.slide_out_right = AnimationUtils.loadAnimation(this.context, android.R.anim.slide_out_right);


        this.mainSwitch = (ViewSwitcher) this.mainView.findViewById(R.id.viewSwitcher1);
        this.quizzSwitch = (ViewSwitcher) this.mainView.findViewById(R.id.viewSwitcherQuizz);

        this.mainSwitch.setInAnimation(this.slide_in_left);
        this.mainSwitch.setOutAnimation(this.slide_out_right);
        this.quizzSwitch.setInAnimation(this.slide_in_left);
        this.quizzSwitch.setOutAnimation(this.slide_out_right);


        this.hint = (Button) v.findViewById(R.id.indication);
        this.hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue)
            {
                Log.d("Indication ", "Checkem");
                mainSwitch.showNext();
            }
        });


        this.quizz = (Button) this.mainView.findViewById(R.id.quizz);
        this.quizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue)
            {
                mainSwitch.showPrevious();
            }
        });





        //Load content to the specific elements of the view
        this.setOnQuizz = true;
        this.initializeRadio();
        this.refreshUser(question);



    }

    public void insertIntoMainLayout(Question question)
    {
        //Inflate the Hidden Layout Information View
        this.indicationTextView=new TextView(this.context);
        this.loadTitleQuestion(question);

        FrameLayout myFrame = (FrameLayout) mainView.findViewById(R.id.myframe);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        ViewWithQuestion = inflater.inflate(R.layout.justquestion, myFrame, false);
        myFrame.addView(ViewWithQuestion);

    }

    public void initializeImg(){

        this.quizzSwitch.showNext();
        this.setOnQuizz = false;
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

    public void loadTitleQuestion(Question q){
        this.questionTextView = (TextView) this.mainView.findViewById(R.id.textView1);
        this.questionTextView.setText(q.getQuestion());
    }

    public void loadButton(Question q){
        for(int i=0 ; i<q.getChoices().size();i++)
        {
            radioButtonlist.get(i).setSelected(false);
            radioButtonlist.get(i).setChecked(false);
            radioButtonlist.get(i).setText(q.getChoices().get(i));
        }
        if (!this.setOnQuizz){
            this.quizzSwitch.showPrevious();
            this.setOnQuizz = true;
        }
    }

    public void loadIndication(Question q){

        this.indicationTextView = (TextView) this.mainView.findViewById(R.id.MyindicationText);
        this.indicationTextView.setText(q.getHints().get(0));
    }

    public boolean check_validity(Question q){

        String answer = q.getCorrect();
        int i = 0;
        Boolean correct = false;

        while(i<this.radioButtonlist.size() && !correct){

            if (this.radioButtonlist.get(i).isChecked()){
                //System.out.println("radio button activated : "+i+" "+ " "+this.radioButtonlist.get(i).getText());
                correct = this.radioButtonlist.get(i).getText().equals(answer);
            }
            i++;
        }

        Log.d("Validity:", "sending back the boolean");


        return correct;
    }

    public void refreshUser(Question q){


        Log.d("Refresh", ""+q.toString());
        this.loadTitleQuestion(q);

        if (q.getChoices().size() == 0 && this.setOnQuizz) {
            this.initializeImg();
        }

        if(q.getChoices().size() > 0){
            this.loadButton(q);
        }

        this.loadIndication(q);

    }

    public void changeQuizzToImg() {
        if (this.setOnQuizz) {
            quizzSwitch.showNext();
        } else{
            quizzSwitch.showPrevious();
        }
    }

    public ViewSwitcher getQuizzSwitch() {
        return quizzSwitch;
    }

    public ViewSwitcher getMainSwitch() {
        return mainSwitch;
    }

}
