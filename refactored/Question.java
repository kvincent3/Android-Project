package com.example.englishproject;

import java.util.ArrayList;

public class Question {

	private int id;
	private String question;
	private String correct;
	private ArrayList<String> choices = new ArrayList<String>();
	private ArrayList<String> hints = new ArrayList<String>();
	
	public Question() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

		
	public void addChoice(String a){
		this.choices.add(a);
	}

	public ArrayList<String> getChoices() {
		return choices;
	}

	public void setchoices(ArrayList<String> answers) {
		this.choices = answers;
	}

	public ArrayList<String> getHints() {
		return hints;
	}

	public void setHints(ArrayList<String> hints) {
		this.hints = hints;
	}
	
	public void addHint(String hint) {
		this.hints.add(hint);
	}


	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public String getCorrect() {
		return correct;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", correct="
				+ correct + ", choices=" + choices + ", hints=" + hints + "]";
	}


	
	
}


