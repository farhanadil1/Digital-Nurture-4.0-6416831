package com.cognizant.ormlearn.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String os;
    private double ramSize;
    private String cpu;

    
    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getOs() {
		return os;
	}


	public void setOs(String os) {
		this.os = os;
	}


	public double getRamSize() {
		return ramSize;
	}


	public void setRamSize(double ramSize) {
		this.ramSize = ramSize;
	}


	public String getCpu() {
		return cpu;
	}


	public void setCpu(String cpu) {
		this.cpu = cpu;
	}


	@Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", os=" + os + ", ramSize=" + ramSize + ", cpu=" + cpu + "]";
    }
}
