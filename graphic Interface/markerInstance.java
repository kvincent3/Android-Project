package com.example.englishproject;

import java.util.ArrayList;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class markerInstance {
	LatLng coor;
	String titre;
	String texte;
	String image;
	Boolean complete;
	
	
	//generate marker instance from a string
	public markerInstance(String arguments){
		
		this.complete = false;
		Log.d("Marker creation : ",arguments);			
		
		if (arguments.split(",").length == 5){
			this.complete = true;
		}
		
		String[] args = arguments.split(",");
		int i = 0;
		while (i < args.length){
			if (i == 1){
				this.coor = new LatLng(Double.parseDouble(args[i-1]), Double.parseDouble(args[i]));
			}
			
			if (i==2) {
				this.titre = args[i];
			}
			
			if (i == 3){
				this.texte = args[i];
			}
			
			if (i == 4){
				this.image = args[i];
			}
			i++;
		}
		Log.d("Complete ? ", complete.toString());
		Log.d("Marker instance : ", this.toString());
		
	}

	public LatLng getCoor() {
		return coor;
	}

	public void setCoor(LatLng coor) {
		this.coor = coor;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	@Override
	public String toString() {
		return "markerInstance [coor=" + coor + ", titre=" + titre + ", texte="
				+ texte + ", image=" + image + ", complete=" + complete + "]";
	}
	
	
	
	
	
	
}
