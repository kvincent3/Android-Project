
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
  ArrayList<Question> questions;
  ModelMap relativeMap;
  
  public Learn(Context c, String fileOfData, String relativeMap){
	  	this.questions = new ArrayList<Question>();
		extractData(c, fileOfData);
		setUpMap(c, relativeMap);
	}
  
  public void extractData(Context c, String fileOfData){

		InputStream in;
		BufferedReader br = null;
		try{
			in = c.getAssets().open(fileOfData);	 
			br = new BufferedReader(new InputStreamReader(in));
			
			//Read the file content and fill the ArrayList of Question
			String line;
			Question q = new Question();
			while ((line = br.readLine()) != null){
					
				String[] parts=line.split(":");
				
				
				if (parts[0].equals("q")){
					q.setQuestion(parts[1]);
				}
				else if(parts[0].equals("id")){
					q.setId(Integer.parseInt(parts[1]));
				}
				else if (parts[0].equals("c")){
					q.addChoice(parts[1]);
				}
				else if (parts[0].equals("ans")){
					q.setCorrect(parts[1]);
				}
				else if (parts[0].equals("ind")){
					q.addHint(parts[1]);
				}
				
				if (line.equals("===")){
					Log.d("Insertion : ", q.toString());
					this.questions.add(q);
					q = null;
					q = new Question();
				}
				
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
  
  	public void setUpMap(Context c, String relativeMap){
  		this.relativeMap = new ModelMap(c, relativeMap);
  	}
  	
  	
  	public ModelMap getRelativeMap() {
		return relativeMap;
	}

	public void setRelativeMap(ModelMap relativeMap) {
		this.relativeMap = relativeMap;
	}

	public Question GiveMeDataGame(int id) {
		for (int i = 0; i <= this.questions.size(); i++) {
			if (this.questions.get(i).getId() == id) {
				return this.questions.get(i);
			}
		}
		return null;
	}

}
