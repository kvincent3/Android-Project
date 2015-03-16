package com.example.englishproject;

import java.util.ArrayList;

public class ModelReponseAndIndication 
{
	ArrayList<String> reponse;
	String indication;
	public ModelReponseAndIndication(String indication,ArrayList<String> reponse) 
	{
		this.reponse=reponse;
		this.indication=indication;
	}
	public String getIndication()
	{
		return this.indication;
	}

	public ArrayList<String> getReponse()
	{
		return this.reponse;
	}
	
	public void setIndication(String in)
	{
		this.indication=in;
	}
	
	public void AddReponse(String Rep)
	{
		this.reponse.add(Rep);
	}

}
