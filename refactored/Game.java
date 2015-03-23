package com.example.englishproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.print.PrintAttributes;
import android.util.Log;

public class Game 
{
  Context context;
  String File;
  ArrayList<DataGame> gameContent = new ArrayList<DataGame>();
  DataGame dataGame= new DataGame();
  
  public Game(Context c,String name)
  {
	  this.File=name;
	  this.context=c;
  }
  
  public void ReadFile(String name)
  {
	  	//fill in dataGame with the game content (extract from name.txt)
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
				do{
					PutDataIn(line);
				}
				while (!line.equals("===") && (line = reader.readLine()) != null);
				
				this.gameContent.add(this.dataGame);
				this.dataGame=new DataGame();
			  }
			  
			  for (int i=0;i<this.gameContent.size();i++) 
			  {
				  Log.d("monid:", ""+this.gameContent.get(i).id);
				  Log.d("question :", ""+this.gameContent.get(i).question);
				  Log.d("reponse :", ""+this.gameContent.get(i).reponse);
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
		  this.dataGame.setQuestion(parts[1]);
	  }
	  else if(parts[0].equals("id"))
	  {
		  this.dataGame.setId(Integer.parseInt(parts[1]));
	  }
	  else if (parts[0].equals("r")) 
	  {
		  this.dataGame.AddResponse(parts[1]);
	  }
	  else if (parts[0].equals("ind"))
	  {
		  this.dataGame.setIndication(parts[1]);
	  }
	  else if (parts[0].equals("p"))
	  {
		  this.dataGame.setVille(parts[1]);
	  }
  }
  
  public DataGame GiveMeDataGame(int id)
  {
	  Log.d("SIZE :","size of gameContent :"+this.gameContent.size());
	  for (int i=0;i<=this.gameContent.size();i++)
	  {
		  if (this.gameContent.get(i).getId()==id)
		  {
			  return this.gameContent.get(i);
		  }
	  }
	  return null;
  }

public Context getContext() {
	return context;
}

public void setContext(Context context) {
	this.context = context;
}

public String getFile() {
	return File;
}

public void setFile(String file) {
	File = file;
}

public ArrayList<DataGame> getGameContent() {
	return gameContent;
}

public void setGameContent(ArrayList<DataGame> gameContent) {
	this.gameContent = gameContent;
}

public DataGame getDataGame(int i) {
	try{
		return this.gameContent.get(i);
	}catch (NullPointerException e){
		System.out.println(e.toString());
		return null;
	}
	
}


public void setDataGame(DataGame dataGame) {
	this.dataGame = dataGame;
}
  
  
  

}
