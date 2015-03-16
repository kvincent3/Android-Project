package com.example.englishproject;





import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.MapFragment;

import android.app.Activity; 
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends Activity { 
	
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(51.5085300 , -0.1257400);
	
	static final LatLng LIVERPOOL = new LatLng(53.566414,-5.010254);
	
	
	private GoogleMap map;

	View view;
   
	@Override
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	
		//on sauvegarde la vue principale pour la mettre en paramètre des autres vues
		this.view = getWindow().getDecorView().findViewById(android.R.id.content);		
		setContentView(R.layout.activity_main);
		
		//Associer le fragment map � la variable de type googleMap

	    
		Learn mytestlearn = new Learn(this, "pourTester.txt");
		
		if (mytestlearn.GiveMeDataGame(2) != null)// si on a réussi a extraire le informations avec l'id 2
		{
			DataGame courant = mytestlearn.GiveMeDataGame(2); // on sauvegarde les données du jeu que l'on a lu
			ModelBar mytestmodel = new ModelBar(30,courant.getQuestion(),0);// on a le model ModelBar qui enregistre ce que l'on va paser à la vue
			ModelMap myModelMap  = new ModelMap(this,courant.getVille(),"mapdata.txt");
			ModelReponseAndIndication mytestmodelreponse = new ModelReponseAndIndication(courant.getIndication(), courant.getReponse());//On sauvegarde le modelReponseIndication qui enregistre ce que l'on passe à la vue
			ViewMap viewmap =new ViewMap(this, map, myModelMap);
			if (courant.getReponse().size()>1)//si on a plusieurs reponse on est dans le mode question-reponse
			{
		          ViewReponseAndIndication test2=new ViewReponseAndIndication(this.view, this);//la vue reponse indication qui prend la vue et le context de la vue principale
		          
		          test2.InitializeRadio(mytestmodelreponse);//on initialise cette vue avec le modelBar
			}
			else//si non on est dans le mode on touche la carte
			{
				 new ViewIndicationMap(this.view, this);//ce que l'on affiche dans le cadre de la carte
			}
			ViewBarTop myTest= new ViewBarTop(this.view, this,mytestmodel);//on donne le modelBar a la vue associé
			myTest.InitializeTextView();//on innitialise les paramètres
		}
	

}
	}

