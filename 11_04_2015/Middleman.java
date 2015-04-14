package com.example.englishproject;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
    private ModelBar modBar;
    private String NewPlace;
    public boolean mustRefresh;

    public Middleman(Context c, String fileOfQuestions, String fileOfLocations){
        this.c = c;
        //instanciate the model via different files
        this.extractQuestions(c, fileOfQuestions);
        this.extractLocations(c, fileOfLocations);
        //instanciate a generic header front
        this.modBar = new ModelBar(30, "QUIZZ", 0);
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

            //Read the file content and fill the ArrayList of Question
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
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Location getAppropriateLocation()
    {
    	Location l = new Location();
    		for (int j=0;j<this.modMap.size();j++)
    		{
    			Log.d("eng",this.NewPlace);
    			Log.d("boucle",this.modMap.get(j).getName());
    			if (this.modMap.get(j).getName().equals(this.NewPlace))
    			{
    				l= this.modMap.get(j); 
    			}
    		}
    	return l;
    }
    
    
    
    //si pas les mêmes renvoie true si non false
    public boolean ComparePlaces()
    {
	    	int place_last_true=0;
	    	boolean test=false;
	     	//Log.d("test5","on compare"); 
	    	for (int i=0;i<this.getModQuestions().size();i++)
	    	{
		        Log.d("placetest"+i,this.getModQuestions().get(i).getPassed()+"");

	    	}
	    	for (int i=0;i<this.getModQuestions().size();i++)
	    	{
	    		
	    		if (this.getModQuestions().get(i).getPassed())
	    		{
	    			place_last_true=i;
	    		}
	    		else
	    		{
	    			break; 
	    		}
	    	}
	        Log.d("placetest",place_last_true+"");
    		if(this.getModQuestions().get(place_last_true).getPlace().equals(this.getModQuestions().get(place_last_true+1).getPlace()))
    		{
    		    test=false;
    		    this.mustRefresh=false;
    		}
    		else
    		{
    			test=true;
    			this.mustRefresh=true;
    			this.NewPlace=this.getModQuestions().get(place_last_true+1).getPlace();
    			Log.d("test5",this.NewPlace);
    		}
    	Log.d("test",""+test);
    	return test;
    	
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
        for (int i = 0; i < this.modQuestions.size(); i++) {
            if (this.modQuestions.get(i).getId() == id) {
                return this.modQuestions.get(i);
            }
        }
        return null;
    }

    public Location giveMeMap(String place) {
        for (int i = 0; i < this.modMap.size(); i++) {
            if (this.modMap.get(i).getName().equals(place))
            {
                return this.modMap.get(i);
            }
        }
        return null;
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
    

    @Override
    public String toString() {
        return "Middleman{"+
                ", modQuestions=" + modQuestions +
                ", modMap=" + modMap +
                ", modBar=" + modBar +
                '}';
    }
}