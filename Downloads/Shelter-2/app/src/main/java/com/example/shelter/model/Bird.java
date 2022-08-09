package com.example.shelter.model;

public class Bird extends Animal implements java.io.Serializable {

	public Bird() {
	}

	public Bird(String shelter_id, String animal_name, String animal_id, double weight,
			Long receipt_date) {
		super(shelter_id, "bird", animal_name, animal_id, weight, receipt_date);
	
	}

}
