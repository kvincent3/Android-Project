package com.example.englishproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class ModelMap 
{
  String ville;
  Context c;
  DataMap data;
  ArrayList<DataMap> chargement ;
  public ModelMap(Context c,String ville,String name)
  {
	  this.chargement = new ArrayList<DataMap>();
	  this.data= new DataMap();
	  this.ville=ville;
	  this.c=c;
	  ReadFile(name);
  }

  public void ReadFile(String name)
  {
		InputStream in;
		try 
		{
			in = this.c.getAssets().open(name);	 
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line="";
			try 
			{
			  while ((line = reader.readLine()) != null)
			  {
				do
				{
					PutDataIn(line);
				}
				while (!line.equals("===") && (line = reader.readLine()) != null);
				this.chargement.add(this.data);
				this.data=new DataMap();
			  }
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			try 
			{
				reader.close();
			} 
			catch (IOException e) 
			{
				
				e.printStackTrace();
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
  }
  
  private void PutDataIn(String line) 
  {
	  String[] parts=line.split(":");
	  if (parts[0].equals("p"))
	  {
		  this.data.setVille(parts[1]);
	  }
	  else if(parts[0].equals("m"))
	  {
		  String[] info=parts[1].split(",");
		  Log.d("test", info[0]);
		  this.data.AddLatlng(new LatLng(Double.parseDouble(info[0]), Double.parseDouble(info[1])));
		  Log.d("test", info[1]);
		  this.data.AddTitre(info[2]);
		  this.data.AddText(info[3]);
		  this.data.AddImage(info[4]);
		  /*try {
	            // get input stream
	            InputStream ims = this.c.getAssets().open("avatar.jpg");
	            // load image as Drawable
	            Drawable d = Drawable.createFromStream(ims, null);
	            // set image to ImageView
	            mImage.setImageDrawable(d);
	        }
	        catch(IOException ex) {
	            return;
	        }*/
	  }
	  else if (parts[0].equals("c"))
	  {
		  String[] info=parts[1].split(",");
		  this.data.setCoorVille(new LatLng(Double.parseDouble(info[0]), Double.parseDouble(info[1])));
	  }
	  else if (parts[0].equals("z"))
	  {
		  this.data.setZoom(Integer.parseInt(parts[1]));
	  }
  }
  
  public ArrayList<DataMap> getDataMap()
  {
	  return this.chargement;
  }
  
  public String getVille()
  {
	  return this.ville;
  }
}
