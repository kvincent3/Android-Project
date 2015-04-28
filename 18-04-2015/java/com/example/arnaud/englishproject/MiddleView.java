package com.example.englishproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class MiddleView 
{
    private Activity context;
	private ViewUserPanel viewUserPanel;
	private ViewMap viewMap;
    private ViewBarTop viewBarTop;
	private Button check;
    private Middleman model;
    private int currentQuestion;
    private Boolean end, refreshMap;

	public MiddleView (Activity a, View mainView, final Middleman model, boolean toTouch){

        this.context = a;
        this.model = model;
        this.currentQuestion = 0;

        this.refreshMap = true;
        this.end = false;

		this.viewUserPanel = new ViewUserPanel(mainView, a, model.giveMeQuestion(currentQuestion), toTouch);
		this.viewMap = new ViewMap(a, toTouch);

        String place = model.giveMeQuestion(currentQuestion).getPlace();
        this.viewMap.InitialiseMap(model.getLocationFromPlace(place));
        this.viewBarTop = new ViewBarTop(mainView, a, model);

        this.check = (Button) a.findViewById(R.id.check);
        this.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue)
            {
                Log.d("Check ", "Checkem");

                if ( (viewMap.getToTouch() && viewMap.checkAccurancy(model.giveMeQuestion(currentQuestion).getCorrect()) )
                        || (viewUserPanel.check_validity(model.giveMeQuestion(currentQuestion))) ) {
                    displayStatus(true);
                    updateModel();

                    if (currentQuestion + 1 < model.getModQuestions().size() && currentQuestion + 1 < model.getMaxNumberQuestion()) {
                        currentQuestion++;

                        viewUserPanel.refreshUser(model.giveMeQuestion(currentQuestion));

                        if (refreshMap) {
                            String place = model.giveMeQuestion(currentQuestion).getPlace();
                            viewMap.refreshMap(model.getLocationFromPlace(place));
                        }

                        viewBarTop.refresh(true);

                    } else {
                        //Log.d("VIEW", "A court de question : " + currentQuestion + " VS " + model.getModQuestions().size());
                        Log.d("END", "redirection");
                        end = true;
                    }
                    synchronized (this) {

                        this.notifyAll();
                    }
                }else if (!viewBarTop.isGoOn()){
                    Log.d("END", "timer is dead --> End of the game");

                    end = true;
                }else{
                    displayStatus(false);
                    viewBarTop.refresh(false);
                }

                if (end){
                    Intent intent = new Intent(context, End.class);
                    intent.putExtra("point",""+viewBarTop.getModel().getPoints());
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }


            }
        });
	}
	
	public MiddleView (Activity a,Middleman m)
	{
		this.context =a;
		this.viewMap = new ViewMap(this.context);
		Log.d("test2","ouaiii");
		this.viewMap.LoadHistoryMap(m.getModHistory()); 
	}


    public void updateModel(){
        //Map & quizz

        //0 : check if the next question is a quizz or a touch !
        //not important there, cause the map is init with toTouch = true, as the loaded file is known as hybrid

        //1 : update question
        this.model.giveMeQuestion(this.currentQuestion).setPassed(true);

        //2:update Map
        //Refreshed if newMap, or the current question was to find a specific place
        //--> the user should have move the map
        Boolean haveFound = this.model.giveMeQuestion(this.currentQuestion).getChoices().size() == 0;
        this.refreshMap = this.model.comparePlaces() || haveFound;
    }

    public void displayStatus(boolean status){
        if (status){
            Toast.makeText(this.context, "Correct ! ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this.context, "Wrong ! ", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean getEnd(){
        return this.end;
    }
}
