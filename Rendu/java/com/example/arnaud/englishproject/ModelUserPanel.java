package com.example.arnaud.englishproject;
//Totally useless
        import java.util.ArrayList;

public class ModelUserPanel
{
    ArrayList<String> reponse;
    String indication;

    public ModelUserPanel(String indication,ArrayList<String> reponse)
    {
        this.reponse=reponse;
        this.indication=indication;
    }
    public String getIndication(){
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
