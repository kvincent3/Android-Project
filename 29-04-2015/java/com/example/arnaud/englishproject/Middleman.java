package com.example.arnaud.englishproject;

import android.content.Context;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Arnaud on 13/04/2015.
 * This class is the middleman between the mainActivity class won't create the model anymore !
 * It will create the rest of the Model, and call constructors of the related views
 *
 */
public class Middleman {

    private Context c;
    //MODELS
    private ArrayList<Question> modQuestions = new ArrayList<Question>();
    private ArrayList<Location> modMap = new ArrayList<Location>();
    private ArrayList<DataHistory> modHistory= new ArrayList<DataHistory>();
	private ModelBar modBar;
    private String newPlace;
    public boolean mustRefresh;
    private int questionNumber=1;
    private int maxNumberQuestion=13;//on met 13 question pour l'instant



    public Middleman(Context c, String fileOfQuestions, String fileOfLocations){
        this.c = c;

        this.extractQuestions(c, fileOfQuestions);
        this.extractLocations(c, fileOfLocations);
        //instanciate a generic header front
        this.modBar = new ModelBar(30, "QUIZZ", 0);
    }
    
    public Middleman(Context c, String fileHistory){
        this.c = c;
        this.extractHistory(c, fileHistory);
    }


    private void extractHistory(Context c, String fileHistory)
    {
        InputStream in;
        BufferedReader br = null;
        try{
            in = c.getAssets().open(fileHistory);
            br = new BufferedReader(new InputStreamReader(in));
            String line;
            MarkerInstance i = null;
            String story = null;
            while ((line = br.readLine()) != null){

                String[] parts=line.split(":");
                if (parts[0].equals("m")) {
                    i = new MarkerInstance(parts[1]);
                }
                else if (parts[0].equals("h"))
                {
                	story = parts[1];
                }
                if (line.equals("===") && i != null && story != null){
                    DataHistory q = new DataHistory(i, story);
                    this.modHistory.add(q);
                    i = null;
                    story = null;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (br != null)br.close();
                //Randomize the arrayList
                long seed = System.nanoTime();
                Collections.shuffle(this.modQuestions, new Random(seed));

            } catch (IOException ex) {
                ex.printStackTrace();


            }
        }
		
	}

	/*
    Extract questions
     */
    public void extractQuestions(Context c, String fileOfQuestions){

        InputStream in;
        BufferedReader br = null;
        try{

            in = c.getAssets().open(fileOfQuestions);
            br = new BufferedReader(new InputStreamReader(in));

            String line;
            Question q = new Question();
            while ((line = br.readLine()) != null){

                String[] parts=line.split(":");

                if (parts[0].equals("q")){
                    q.setQuestion(parts[1]);
                }
                else if(parts[0].equals("id")){
                    q.setId(Integer.parseInt(parts[1]));
                }
                else if (parts[0].equals("c")){
                    q.addChoice(parts[1]);
                }
                else if (parts[0].equals("ans")){
                    q.setCorrect(parts[1]);
                }
                else if (parts[0].equals("ind")){
                    q.addHint(parts[1]);
                }
                else if (parts[0].equals("p")){
                    q.setPlace(parts[1]);
                }

                if (line.equals("===")){
                    Log.d("Insert of question : ", q.toString());
                    this.modQuestions.add(q);
                    q = null;
                    q = new Question();
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (br != null)br.close();
                //Randomize the arrayList
                long seed = System.nanoTime();
                Collections.shuffle(this.modQuestions, new Random(seed));

            } catch (IOException ex) {
                ex.printStackTrace();


            }
        }
    }

    /*
    Extract Locations
     */
    public void extractLocations(Context c, String fileOfLocations) {
        InputStream in;
        BufferedReader br = null;
        try {
            in = c.getAssets().open(fileOfLocations);
            br = new BufferedReader(new InputStreamReader(in));

            // Read the file content and fill the arrayList of locations
            String line;
            Location loc = new Location();
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(":");

                if (parts[0].equals("p")) {
                    loc.setName(parts[1]);
                } else if (parts[0].equals("m")) {
                    loc.addMarkers(new MarkerInstance(parts[1]));
                } else if (parts[0].equals("z")) {
                    loc.setZoom(Integer.parseInt(parts[1]));
                } else if (parts[0].equals("c")) {
                    String[] info = parts[1].split(",");
                    loc.setGps(new LatLng(Double.parseDouble(info[0]),
                            Double.parseDouble(info[1])));
                }

                if (line.equals("===")){
                    Log.d("Insert of location : ", loc.toString());
                    this.modMap.add(loc);
                    loc = null;
                    loc = new Location();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }


    /*
    Appropriate functions
     */
    public Question giveMeQuestion(int id) {

        if (id < this.getModQuestions().size())
            return this.getModQuestions().get(id);
        return null;
    }

    //actualise la valeur de this.mustRefresh
    //renvoit this.mustRefresh
    //Donc si renvoit true, on doit rafraichir
    public boolean comparePlaces()
    {
        int place_last_true=0;

        int i=0;
        while(i<this.getModQuestions().size()){
            if (this.getModQuestions().get(i).getPassed())
            {
                place_last_true=i;
            }
            i++;
        }

        Log.d("placetest",place_last_true+"");
        String a = this.getModQuestions().get(place_last_true).getPlace().toLowerCase();

        //Surveiller la valeur de place_last_true+1
        String b;
        if (place_last_true + 1 < this.getModQuestions().size()){
            b = this.getModQuestions().get(place_last_true+1).getPlace().toLowerCase();
        }else{
            //On met b a la meme valeur que a
            b = this.getModQuestions().get(place_last_true).getPlace().toLowerCase();;
        }

        if(a.equals(b))
        {
            this.mustRefresh=false;
            Log.d("CmpPlace", "place identiques");
        }
        else
        {
            this.mustRefresh=true;
            this.newPlace = this.getModQuestions().get(place_last_true+1).getPlace().toLowerCase();
            Log.d("CmpPlace", "place differente --> "+this.newPlace);
        }
        Log.d("CmpPlace","return : " + this.mustRefresh);
        return this.mustRefresh;

    }

    public Location getLocationFromPlace(String place)
    {
        this.newPlace = place;

        Location l=null;
        for (int j=0;j<this.modMap.size();j++)
        {
            if (this.modMap.get(j).getName().equals(this.newPlace))
            {
                l = new Location();
                l = this.modMap.get(j);


            }
        }
        return l;
    }



    /*
    Generic getters and setters
     */


    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<Question> getModQuestions() {
        return modQuestions;
    }

    public void setModQuestions(ArrayList<Question> modQuestions) {
        this.modQuestions = modQuestions;
    }

    public ArrayList<Location> getModMap() {
        return modMap;
    }

    public void setModMap(ArrayList<Location> modMap) {
        this.modMap = modMap;
    }

    public ModelBar getModBar() {
        return modBar;
    }

    public void setModBar(ModelBar modBar) {
        this.modBar = modBar;
    }


    public int getQuestionNumber() {
        return questionNumber;
    }


    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }


    public int getMaxNumberQuestion() {
        return maxNumberQuestion;
    }


    public void setMaxNumberQuestion(int maxNumberQuestion) {
        this.maxNumberQuestion = maxNumberQuestion;
    }


    @Override
    public String toString() {
        return "Middleman{"+
                ", modQuestions=" + modQuestions +
                ", modMap=" + modMap +
                ", modBar=" + modBar +
                '}';
    }

    public ArrayList<DataHistory> getModHistory() {
		return modHistory;
	}

	public void setModHistory(ArrayList<DataHistory> modHistory) {
		this.modHistory = modHistory;
	}



}