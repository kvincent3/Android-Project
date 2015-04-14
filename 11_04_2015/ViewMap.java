package com.example.englishproject;



import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;



public class ViewMap
{
    private Middleman middleman;
    private GoogleMap map;
    //instanciate a specific view on a specific map
    public ViewMap(Activity a, GoogleMap map,Middleman m)
    {
        this.middleman=m;
        this.map=map;
        InitialiseMap(a);
        
    }

    //Initialize the map relative to the exercise
    //So many tests disappear...
    //ie : if city in the current one etc...
    public void InitialiseMap(Activity a)
    {
        map = ((MapFragment) a.getFragmentManager().findFragmentById(R.id.map) ).getMap();
        Location l = this.middleman.giveMeMap( this.middleman.giveMeQuestion(0).getPlace() );
        if (map!=null){

            CameraUpdate center = CameraUpdateFactory.newLatLng(l.getGps());
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(l.getZoom());
            map.moveCamera(center);
            map.animateCamera(zoom);

            for (int j=0 ; j < l.getMarkers().size(); j++)
            {
                map.addMarker(new MarkerOptions().position(l.getMarkers().get(j).getCoor())
                        .title(l.getMarkers().get(j).getTitre())
                        .snippet(l.getMarkers().get(j).getTexte())
                        .icon(BitmapDescriptorFactory.fromAsset(l.getMarkers().get(j).getImage())));
            }
        }
    }

    public void Refresh()
    {
        if (map!=null && this.middleman.ComparePlaces())
        {
        	Location l = this.middleman.getAppropriateLocation();
        	if (l!=null)
        	{
	        	map.clear();
	            CameraUpdate center = CameraUpdateFactory.newLatLng(l.getGps());
	            CameraUpdate zoom = CameraUpdateFactory.zoomTo(l.getZoom());
	            map.moveCamera(center);
	            map.animateCamera(zoom);
	            for (int j=0 ; j < l.getMarkers().size(); j++)
	            {
	                map.addMarker(new MarkerOptions().position(l.getMarkers().get(j).getCoor())
	                        .title(l.getMarkers().get(j).getTitre())
	                        .snippet(l.getMarkers().get(j).getTexte())
	                        .icon(BitmapDescriptorFactory.fromAsset(l.getMarkers().get(j).getImage())));
	            }
	            synchronized (this)
	            {
	            	Log.d("notifie","on notfie");
	                this.notify();
	            }
        	}
        }
    }
    /*
    We should add a refreshMap
     */
}
