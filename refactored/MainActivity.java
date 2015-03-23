package com.example.englishproject;






import com.google.android.gms.maps.GoogleMap;
import android.app.Activity; 
import android.os.Bundle;
import android.util.Log;
import android.view.View;



public class MainActivity extends Activity { 
	private GoogleMap map;

	View view;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	
		//on sauvegarde la vue principale pour la mettre en paramÃ¨tre des autres vues
		this.view = getWindow().getDecorView().findViewById(android.R.id.content);		
		setContentView(R.layout.activity_main);
		
		//Get all the file content about one city
		Learn mytestlearn = new Learn(this, "pourTester.txt", "mapdata.txt");
		
		if (mytestlearn.GiveMeDataGame(0) != null){
			System.out.println(mytestlearn.GiveMeDataGame(0).toString());
			Log.d("QUESTION :", mytestlearn.GiveMeDataGame(0).toString());
		}
		
		
		Question courant = mytestlearn.GiveMeDataGame(0);
		System.out.println(courant.toString());
		
		ModelBar upBar = new ModelBar(30,courant.getQuestion(),0);
		
		//generate an instance of current hints and answers in order to display them
		ModelUserPanel userPanel = new ModelUserPanel(courant.getHints().get(0), courant.getChoices());
		
		
		ViewBarTop header= new ViewBarTop(this.view, this, upBar);
		header.InitializeTextView();
		
		ViewMap relativeMap = new ViewMap(this, map, mytestlearn.getRelativeMap());
		
		if (courant.getChoices().size()>1){			
			
	        ViewUserPanel userView=new ViewUserPanel(this.view, this, courant); //On passe le modele à al vue : la question	          
	        userView.InitializeRadio(userPanel);
		}
		else{
			 new ViewIndicationMap(this.view, this);
		}
		
		
		/*
		 * Ajouter un bouton au userPanel pour valider les quiz
		 * Ajouter des triggers sur la map pour valider une map
		 * Ajouter un reponsechecker --> voir si la reponse cochée est bien celle attendue
		 */
		
	}
}

