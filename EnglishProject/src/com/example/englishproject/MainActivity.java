package com.example.englishproject;





import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
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
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	
	static final LatLng LIVERPOOL = new LatLng(53.566414,-5.010254);
	
	
	private GoogleMap map;

	View view;
   
	@Override
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	
		//on sauvegarde la vue principale pour la mettre en paramÃ¨tre des autres vues
		this.view = getWindow().getDecorView().findViewById(android.R.id.content);		
		setContentView(R.layout.activity_main);
		
		//Associer le fragment map à la variable de type googleMap
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map) ).getMap();
		
		
		/*
		 * On peut placer la map a des coordonnées GPS bien précises;
		 * Mais, cela empêche d'avoir le zoom adéquat pôur afficher le Royaume uni en entier
		 * Voir avec GeoCoder / Mapcontroller zoomtospan / fitbound	/
		 */
	
		/*
		 * Polygon
		 *  GoogleMap map;
			 // ... get a map.
			 // Add a triangle in the Gulf of Guinea
			 Polygon polygon = map.addPolygon(new PolygonOptions()
			     .add(new LatLng(0, 0), new LatLng(0, 5), new LatLng(3, 5), new LatLng(0, 0))
			     .strokeColor(Color.RED)
			     .fillColor(Color.BLUE));
			     
			     
					ArrayList<LatLng> decodedPoints = (ArrayList<LatLng>) Polygon.decode(step);
		for (LatLng point : decodedPoints) {
		    boundsBuilder.include(point);
		}
		 */

		//Ajoute un polygone coloré sur la map ; Contour = rouge / Intérieur = Bleu
		/*
		Polygon polygon = map.addPolygon(new PolygonOptions()
	     .add(new LatLng(49.738682, -10.975342), new LatLng(49.738682, 2.175293), new LatLng(59.377988, 1.560059), new LatLng(59.377988, -12.897949), new LatLng(49.738682, -10.975342))
	     .strokeColor(Color.RED)
	     .fillColor(Color.BLUE));
		
		
		/*
		Builder boundsBuilder = LatLngBounds.builder();
		boundsBuilder.include(new LatLng(49.738682, -10.975342));
		boundsBuilder.include(new LatLng(49.738682, 2.175293));
		boundsBuilder.include(new LatLng(59.377988, 1.560059));
		boundsBuilder.include(new LatLng(59.377988, -12.897949));
		boundsBuilder.include(new LatLng(49.738682, -10.975342));				
		LatLngBounds bounds = boundsBuilder.build();		
		map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
		
		////////////////////////////////////////////////////////////////
		 * private GoogleMap mMap;
			// Create a LatLngBounds that includes Australia.
			private LatLngBounds AUSTRALIA = new LatLngBounds(
			  new LatLng(-44, 113), new LatLng(-10, 154));
			
			// Set the camera to the greatest possible zoom level that includes the
			// bounds
			mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(AUSTRALIA, 0));
					 */

	
		//LatLngBounds area = new LatLngBounds(new LatLng(40.025721, -175.498062), new LatLng(60.317032, -180.041681));		 
		//map.moveCamera(CameraUpdateFactory.newLatLngBounds(area, 308, 150, 0));
		
		
		
		LatLngBounds UK = new LatLngBounds(
		  new LatLng(47.383639452689664, -2.39866406249996), new LatLng(62.53530451232491, 0.0));

		map.moveCamera(CameraUpdateFactory.newLatLngZoom(UK.getCenter(), 5));
		
		
		
		/*
		//placement de marqueurs sur la carte
		Marker liverpool = map.addMarker(new MarkerOptions()
		        .position(LIVERPOOL));
		
		        .title("Kiel")
		        .snippet("Kiel is cool")
		        .icon(BitmapDescriptorFactory
		            .fromResource(R.drawable.ic_launcher)));
		  */         

	    // Move the camera instantly to hamburg with a zoom of 6.
		//map.moveCamera(CameraUpdateFactory.newLatLngZoom(LIVERPOOL, 6));
		//map.moveCamera(CameraUpdateFactory.zoomTo(6));

	    // Zoom in, animating the camera.
	    //map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		
	    // on lit le fichier "pourTester.txt"
		Learn mytestlearn = new Learn(this, "pourTester.txt");
		
		if (mytestlearn.GiveMeDataGame(2) != null)// si on a rÃ©ussi a extraire le informations avec l'id 2
		{
			DataGame courant = mytestlearn.GiveMeDataGame(2); // on sauvegarde les donnÃ©es du jeu que l'on a lu
			
			ModelBar mytestmodel = new ModelBar(30,courant.getQuestion(),0);// on a le model ModelBar qui enregistre ce que l'on va paser Ã  la vue
			
			ModelReponseAndIndication mytestmodelreponse = new ModelReponseAndIndication(courant.getIndication(), courant.getReponse());//On sauvegarde le modelReponseIndication qui enregistre ce que l'on passe Ã  la vue
			
			if (courant.getReponse().size()>1)//si on a plusieurs reponse on est dans le mode question-reponse
			{
		          ViewReponseAndIndication test2=new ViewReponseAndIndication(this.view, this);//la vue reponse indication qui prend la vue et le context de la vue principale
		          
		          test2.InitializeRadio(mytestmodelreponse);//on initialise cette vue avec le modelBar
			}
			else//si non on est dans le mode on touche la carte
			{
				 new ViewIndicationMap(this.view, this);//ce que l'on affiche dans le cadre de la carte
			}
			ViewBarTop myTest= new ViewBarTop(this.view, this,mytestmodel);//on donne le modelBar a la vue associÃ©
			myTest.InitializeTextView();//on innitialise les paramÃ¨tres
		}
	

}
	}

