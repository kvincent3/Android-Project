package com.example.englishproject;

import java.util.ArrayList;

public class DataGame {
int id;
String question ;
ArrayList<String> reponse = new ArrayList<String>();
String ville;
String indication;

public String getQuestion()
{
	return question;
}

public int getId()
{
	return id;
}

public String getIndication()
{
	return this.indication;
}

public String getVille()
{
	return this.ville;
}

public void setVille(String ville)
{
	this.ville=ville;
}
public void setIndication(String a)
{
	this.indication=a;
}
public ArrayList<String> getReponse()
{
	return reponse;
}

public void setId(int a)
{
	this.id=a;
}
public void setQuestion(String s)
{
	this.question=s;
}
public void AddResponse(String a)
{
	this.reponse.add(a);
}
}
