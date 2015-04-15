package com.example.englishproject;



public class ModelBar
{
    private int delai;
    private String question;
    private int points;



    public ModelBar(int del,String quest,int pts )
    {
        this.delai= del;
        this.question= quest;
        this.points= pts;
    }

    public int getDelai()
    {
        return this.delai;
    }

    public String getQuestion()
    {
        return this.question;
    }

    public int getPoints()
    {
        return this.points;
    }

    public void setDelai(int newdel)
    {
        this.delai=newdel;
    }

    public void setQuestion(String newquest)
    {
        this.question=newquest;
    }

    public void setPoints(int newPoints)
    {
        this.points=newPoints;
    }
	
	public void updatesGoodAnswer(int timer)
	{
		this.setPoints(this.getPoints()+(int)Math.exp((timer)/4));
	}
	public void updatesBadAnswer(int timer)
	{
		this.setPoints(this.getPoints()-(int)Math.exp((30-timer)/5));
	}

}
