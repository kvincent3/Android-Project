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
   ModelMap modelmap;
   public ViewMap(Activity a,GoogleMap map,ModelMap modelmap)
   {
	   this.modelmap=modelmap;
	   InitialiseMap(a,map);
   }
   
   public void InitialiseMap(Activity a,GoogleMap map)
   {
	    map = ((MapFragment) a.getFragmentManager().findFragmentById(R.id.map) ).getMap();
	    if (map!=null){
	    	
		    for (int i=0;i<this.modelmap.getDataMap().size();i++)
		    {
		    	if (this.modelmap.getDataMap().get(i).getVille().equals(this.modelmap.getVille()))
		    	{
		    	    CameraUpdate center= CameraUpdateFactory.newLatLng(this.modelmap.getDataMap().get(i).getCoorVille());
		    	    CameraUpdate zoom=CameraUpdateFactory.zoomTo(this.modelmap.getDataMap().get(i).getZoom());
		            map.moveCamera(center);
		    	    map.animateCamera(zoom);
		    		for (int j =0 ; j< this.modelmap.getDataMap().get(i).getText().size(); j++)
		    		{
			     	     map.addMarker(new MarkerOptions().position(this.modelmap.getDataMap().get(i).getCoor().get(j))
			            .title(this.modelmap.getDataMap().get(i).getTitre().get(j))
			            .snippet(this.modelmap.getDataMap().get(i).getText().get(j))
			            .icon(BitmapDescriptorFactory.fromAsset(this.modelmap.getDataMap().get(i).getImage().get(j))));
		    		}
		    	}
		    }
	    }
   }
}
