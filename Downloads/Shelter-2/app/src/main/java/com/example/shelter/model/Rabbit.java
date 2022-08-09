package com.example.shelter.model;

public class Rabbit extends Animal implements java.io.Serializable {

	public Rabbit() {

	}

	public Rabbit(String shelter_id, String animal_name, String animal_id, double weight,
			Long receipt_date) {
		super(shelter_id, "rabbit", animal_name, animal_id, weight, receipt_date);
		// TODO Auto-generated constructor stub
	}

}
