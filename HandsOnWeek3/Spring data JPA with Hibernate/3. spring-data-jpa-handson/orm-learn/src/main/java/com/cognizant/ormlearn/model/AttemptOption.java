package com.cognizant.ormlearn.model;

import jakarta.persistence.*;

@Entity
@Table(name = "attempt_option")
public class AttemptOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ao_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ao_op_id")
    private Option option;

    @ManyToOne
    @JoinColumn(name = "ao_aq_id")
    private AttemptQuestion attemptQuestion;

    @Column(name = "ao_selected")
    private boolean selected;

   

    public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Option getOption() {
		return option;
	}



	public void setOption(Option option) {
		this.option = option;
	}



	public AttemptQuestion getAttemptQuestion() {
		return attemptQuestion;
	}



	public void setAttemptQuestion(AttemptQuestion attemptQuestion) {
		this.attemptQuestion = attemptQuestion;
	}



	public boolean isSelected() {
		return selected;
	}



	public void setSelected(boolean selected) {
		this.selected = selected;
	}



	@Override
    public String toString() {
        return "AttemptOption [id=" + id + ", selected=" + selected + "]";
    }
}
