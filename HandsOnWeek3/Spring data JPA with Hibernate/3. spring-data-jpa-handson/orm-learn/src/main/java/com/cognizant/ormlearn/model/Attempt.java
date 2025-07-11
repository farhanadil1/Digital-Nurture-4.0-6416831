package com.cognizant.ormlearn.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "attempt")
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    private int id;

    @Column(name = "at_date")
    private Date date;

    @Column(name = "at_score")
    private double score;

    @ManyToOne
    @JoinColumn(name = "at_us_id")
    private User user;

    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AttemptQuestion> attemptQuestions;

   
    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public double getScore() {
		return score;
	}


	public void setScore(double score) {
		this.score = score;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Set<AttemptQuestion> getAttemptQuestions() {
		return attemptQuestions;
	}


	public void setAttemptQuestions(Set<AttemptQuestion> attemptQuestions) {
		this.attemptQuestions = attemptQuestions;
	}


	@Override
    public String toString() {
        return "Attempt [id=" + id + ", date=" + date + ", score=" + score + "]";
    }
}
