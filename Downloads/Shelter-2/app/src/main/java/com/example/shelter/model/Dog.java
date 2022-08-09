package com.example.shelter.model;

public class Dog extends Animal implements java.io.Serializable {

	public Dog() {
		
	}

	public Dog(String shelter_id, String animal_name, String animal_id, double weight,
			Long receipt_date) {
		super(shelter_id, "dog", animal_name, animal_id, weight, receipt_date);
	}

}
