package com.example.englishproject;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends Activity {

   View view;
   
   /*
    yoooooo arnaud LIS MOI CA MA POULE !!!
    Alors j ai essayé de faire un MVC mais il n'est pas fini il faut rajouter des paramètres associés à la carte
    Modèle :
      -ModelReponseAndIndication -> contient ce que l'on met dans le fichier comme étant des réponses et des indications 
      -ModelBar -> ce que l'on met dans la barre du haut 
      
    Vue :
       -ViewBarTop: la vue du haut qui connait le modèle ModelBar
       -ViewIndicationMap: la vue du bas lorsque l'on est en mode map
       -viewReponseAndindication: la vue du bas lorsque l'on est en mode question-reponse et connait le modèle ModelReponseAndIndication
       
     Mise à part ca la classe DataGame permet de charger un plateau de jeu 
      en gros si le dans le fichier tu as ca :
        id:2
        q:How old are you version2?
        r:25 years
        r:56 years
        ind:voici une indication 3
      ben DataGame est une structure qui enregistre un id,une question et une liste de réponse et une indication.
      Le wiki n'as pas encore été implémenté!
      
      donc comme tu l es surement compris pour lire le fichier je parse avec la première lettre.On pourra rajouter d'autre lettre si besoin.
      et du coup la classe Learn parse tout le fichier et l'enregistre dans une arraylist de datagame.
      et on utilise la fonction GiveMeDataGame(int id) de la classe learn pour extraire un plateu de jeu.Normlement pour bien écrire cette fonction il faudrait la mettre dans un throw au cas
      où l'id n'est pas retrouvé mais bon la flemme. je ferai peut être après.
      Si tu trouves que la carte est trop grosse n'hésite pas à modifier dans activiti_maiin.xml la hauteur du layout.
      
      et pour finir encore désolé pour les noms qui sont un peu à chier manque d'inspiration
	*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.view=getWindow().getDecorView().findViewById(android.R.id.content);//on sauvegarde la vue principale pour la mettre en paramètre des autres vues
		setContentView(R.layout.activity_main);//on appelle le layout main
		Learn mytestlearn = new Learn(this, "pourTester.txt");// on lit le fichier "pourTester.txt"
		if (mytestlearn.GiveMeDataGame(2) != null)// si on a réussi a extraire le informations avec l'id 2
		{
			DataGame courant = mytestlearn.GiveMeDataGame(2); // on sauvegarde les données du jeu que l'on a lu
			ModelBar mytestmodel = new ModelBar(30,courant.getQuestion(),0);// on a le model ModelBar qui enregistre ce que l'on va paser à la vue
			ModelReponseAndIndication mytestmodelreponse = new ModelReponseAndIndication(courant.getIndication(), courant.getReponse());//On sauvegarde le modelReponseIndication qui enregistre ce que l'on passe à la vue
			if (courant.getReponse().size()>1)//si on a plusieurs reponse on est dans le mode question-reponse
			{
		          ViewReponseAndIndication test2=new ViewReponseAndIndication(this.view, this);//la vue reponse indication qui prend la vue et le context de la vue principale
		          test2.InitializeRadio(mytestmodelreponse);//on initialise cette vue avec le modelBar
			}
			else//si non on est dans le mode on touche la carte
			{
				 new ViewIndicationMap(this.view, this);//ce que l'on affiche dans le cadre de la carte
			}
			ViewBarTop myTest= new ViewBarTop(this.view, this,mytestmodel);//on donne le modelBar a la vue associé
			myTest.InitializeTextView();//on innitialise les paramètres
		}
	

}
	}

