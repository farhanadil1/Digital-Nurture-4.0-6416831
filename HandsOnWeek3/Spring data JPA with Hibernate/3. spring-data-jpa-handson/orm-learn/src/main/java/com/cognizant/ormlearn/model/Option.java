package com.cognizant.ormlearn.model;

import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "op_id")
    private int id;

    @Column(name = "op_text")
    private String text;

    @Column(name = "op_score")
    private double score;

    @ManyToOne
    @JoinColumn(name = "op_qt_id")
    private Question question;

    public int getId() { 
    	return id;
    }
    public void setId(int id) {
    	this.id = id; 
    }

    public String getText() {
    	return text;
    }
    public void setText(String text) { 
    	this.text = text; 
    }

    public double getScore() { 
    	return score; 
    }
    public void setScore(double score) { 
    	this.score = score; 
    }

    public Question getQuestion() {
    	return question; 
    }
    public void setQuestion(Question question) {
    	this.question = question; 
    }

    @Override
    public String toString() {
        return "Option [id=" + id + ", text=" + text + ", score=" + score + "]";
    }
}
