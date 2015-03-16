package com.example.englishproject;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

public class DataMap 
{
   String ville;
   ArrayList<LatLng> coor;
   ArrayList<String> titre;
   ArrayList<String> texte;
   ArrayList<String> image;
   LatLng coorVille;
   int zoom;
   
   public DataMap()
   {
	   this.titre=new ArrayList<String>();
	   this.coor=new ArrayList<LatLng>();
	   this.texte = new ArrayList<String>();
	   this.image=new ArrayList<String>();
   }
   
   public String getVille()
   {
	   return this.ville;
   }
   
   public ArrayList<LatLng> getCoor()
   {
	   return this.coor;
   }
   
   public ArrayList<String> getImage()
   {
	   return this.image;
   }
   
   public ArrayList<String> getTitre()
   {
	   return this.titre;
   }
   
   public ArrayList<String> getText()
   {
	   return this.texte;
   }
   
   public int getZoom()
   {
	   return this.zoom;
   }
   public void setZoom(int z)
   {
	   this.zoom=z;
   }
   public LatLng getCoorVille()
   {
	   return this.coorVille;
   }
   public void setCoorVille (LatLng l)
   {
	   this.coorVille=l;
   }
   public void setVille(String a)
   {
	   this.ville=a;
   }
   
   public void AddText(String a)
   {
	   this.texte.add(a);
   }
   public void AddLatlng(LatLng a)
   {
	   this.coor.add(a);
   }
   
   public void AddTitre(String a)
   {
	   this.titre.add(a);
   }
   public void AddImage(String a)
   {
	   this.image.add(a);
   }
}
