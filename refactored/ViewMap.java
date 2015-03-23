package com.example.englishproject;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ViewMap 
{
   private ModelMap relativeMap;
   
   //instanciate a specific view on a specific map
   public ViewMap(Activity a, GoogleMap map, ModelMap modelmap){
	   this.relativeMap = modelmap;
	   InitialiseMap(a, map);
   }
   
   //Initialize the map relative to the exercise
   //So many tests disappear...
   //ie : if city in the current one etc...
   public void InitialiseMap(Activity a,GoogleMap map)
   {
	    map = ((MapFragment) a.getFragmentManager().findFragmentById(R.id.map) ).getMap();
	    
	    if (map!=null){
	    	
	    	CameraUpdate center = CameraUpdateFactory.newLatLng(this.relativeMap.getCoorVille());
    	    CameraUpdate zoom = CameraUpdateFactory.zoomTo(this.relativeMap.getZoom());
            map.moveCamera(center);
    	    map.animateCamera(zoom);
    	    
    	    for (int j=0 ; j < this.relativeMap.getMarkers().size(); j++)
    		{
	     	     map.addMarker(new MarkerOptions().position(this.relativeMap.getMarkers().get(j).getCoor())
	            .title(this.relativeMap.getMarkers().get(j).getTitre())
	            .snippet(this.relativeMap.getMarkers().get(j).getTexte())
	            .icon(BitmapDescriptorFactory.fromAsset(this.relativeMap.getMarkers().get(j).getImage())));
    		}
	    }
   }
}
