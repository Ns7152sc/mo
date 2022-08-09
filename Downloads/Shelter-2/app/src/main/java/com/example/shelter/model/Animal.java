package com.example.shelter.model;

public class Animal implements java.io.Serializable {

	private String shelter_id;
	private String animal_type;
	private String animal_name;
	private String animal_id;
	private double weight;
	private String unit;
	private Long receipt_date;
	
	public Animal() {}

	public Animal(String shelter_id, String animal_type, String animal_name, String animal_id, double weight,
			Long receipt_date) {
		this.shelter_id = shelter_id;
		this.animal_type = animal_type;
		this.animal_name = animal_name;
		this.animal_id = animal_id;
		this.weight = weight;
		this.receipt_date = receipt_date;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getShelter_id() {
		return shelter_id;
	}

	public void setShelter_id(String shelter_id) {
		this.shelter_id = shelter_id;
	}

	public String getAnimal_type() {
		return animal_type;
	}

	public void setAnimal_type(String animal_type) {
		this.animal_type = animal_type;
	}

	public String getAnimal_name() {
		return animal_name;
	}

	public void setAnimal_name(String animal_name) {
		this.animal_name = animal_name;
	}

	public String getAnimal_id() {
		return animal_id;
	}

	public void setAnimal_id(String animal_id) {
		this.animal_id = animal_id;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Long getReceipt_date() {
		return receipt_date;
	}

	public void setReceipt_date(Long receipt_date) {
		this.receipt_date = receipt_date;
	}

	@Override
	public String toString() {
		return "\n{\n\tshelter_id=" + shelter_id + ",\n\tanimal_type=" + animal_type + ",\n\tanimal_name=" + animal_name
				+ ",\n\tanimal_id=" + animal_id + ",\n\tweight=" + weight + ",\n\treceipt_date=" + receipt_date + "\n}\n";
	}
	
}
