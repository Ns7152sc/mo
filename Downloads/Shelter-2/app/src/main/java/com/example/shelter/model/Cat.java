package com.example.shelter.model;

public class Cat extends Animal implements java.io.Serializable {

	public Cat() {

	}

	public Cat(String shelter_id, String animal_name, String animal_id, double weight,
			Long receipt_date) {
		super(shelter_id, "cat", animal_name, animal_id, weight, receipt_date);

	}

}
