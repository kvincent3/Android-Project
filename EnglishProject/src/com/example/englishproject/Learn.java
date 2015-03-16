package com.example.englishproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class Learn 
{
  Context context;
  String File;
  ArrayList<DataGame> chargement = new ArrayList<DataGame>();
  DataGame datagame= new DataGame();
  
  public Learn(Context c,String name)
  {
	  this.File=name;
	  this.context=c;
	  ReadFile(this.File);
  }
  
  public void ReadFile(String name)
  {
		InputStream in;
		try 
		{
			in = context.getAssets().open(name);	 
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
				this.chargement.add(this.datagame);
				this.datagame=new DataGame();
			  }
			  for (int i=0;i<this.chargement.size();i++) 
			  {
				  Log.d("monid:", ""+this.chargement.get(i).id);
				  Log.d("question :", ""+this.chargement.get(i).question);
				  Log.d("reponse :", ""+this.chargement.get(i).reponse);
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
	  if (parts[0].equals("q"))
	  {
		  this.datagame.setQuestion(parts[1]);
	  }
	  else if(parts[0].equals("id"))
	  {
		  this.datagame.setId(Integer.parseInt(parts[1]));
	  }
	  else if (parts[0].equals("r")) 
	  {
		  this.datagame.AddResponse(parts[1]);
	  }
	  else if (parts[0].equals("ind"))
	  {
		  this.datagame.setIndication(parts[1]);
	  }
	  else if (parts[0].equals("p"))
	  {
		  this.datagame.setVille(parts[1]);
	  }
  }
  
  public DataGame GiveMeDataGame(int id)
  {
	  for (int i=0;i<=this.chargement.size();i++)
	  {
		  if (this.chargement.get(i).getId()==id)
		  {
			  return this.chargement.get(i);
		  }
	  }
	  return null;
  }

}
