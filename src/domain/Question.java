package domain;

import java.io.*;

public class Question implements Serializable, Comparable<Question> {

	private Integer questionNumber;
	private String question; 
	private float betMinimum;
	private String result;  
	private Event event;

	public Question(){
		super();
	}
	
	public Question(Integer queryNumber, String query, float betMinimum, Event event) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
	}
	
	public Question(String query, float betMinimum,  Event event) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;
	}

	public Integer getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}

	public float getBetMinimum() {
		return betMinimum;
	}
	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}

	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum);
	}

	//ordenatzeko set bat delako
	@Override
	public int compareTo(Question o) {
		return this.questionNumber.compareTo(o.questionNumber);
	}
	
}