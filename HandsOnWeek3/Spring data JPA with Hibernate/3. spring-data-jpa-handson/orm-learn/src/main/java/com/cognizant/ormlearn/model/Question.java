package com.cognizant.ormlearn.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qt_id")
    private int id;

    @Column(name = "qt_text")
    private String text;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Option> options;


    

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



	public Set<Option> getOptions() {
		return options;
	}



	public void setOptions(Set<Option> options) {
		this.options = options;
	}



	@Override
    public String toString() {
        return "Question [id=" + id + ", text=" + text + "]";
    }
}
