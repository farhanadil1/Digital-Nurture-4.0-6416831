package com.cognizant.ormlearn.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "attempt_question")
public class AttemptQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aq_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "aq_at_id")
    private Attempt attempt;

    @OneToMany(mappedBy = "attemptQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AttemptOption> attemptOptions;

    @ManyToOne
    @JoinColumn(name = "aq_qt_id")
    private Question question;


   
    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Attempt getAttempt() {
		return attempt;
	}


	public void setAttempt(Attempt attempt) {
		this.attempt = attempt;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	public Set<AttemptOption> getAttemptOptions() {
		return attemptOptions;
	}


	public void setAttemptOptions(Set<AttemptOption> attemptOptions) {
		this.attemptOptions = attemptOptions;
	}


	@Override
    public String toString() {
        return "AttemptQuestion [id=" + id + "]";
    }
}
