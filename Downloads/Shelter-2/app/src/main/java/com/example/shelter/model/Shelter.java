/**
 * This class stores data about each Shelters and exports the data to a JSON file
 * 
 */

package com.example.shelter.model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class Shelter implements java.io.Serializable {
	private boolean receiveAnimals;
	private String shelter_id;
	private String shelterName;
	
	private ArrayList<Animal> animals;
	
	public Shelter(String shelter_id) {
		this.shelter_id = shelter_id;
		receiveAnimals = true;
		this.animals = new ArrayList<>();
	}
	
	public Shelter(String shelter_id, String shelterName) {
		this.shelter_id = shelter_id;
		this.shelterName = shelterName;
		receiveAnimals = true;
		this.animals = new ArrayList<>();
	}
	
	public String getShelter_id() {
		return shelter_id;
	}

	public void setShelter_id(String shelter_id) {
		this.shelter_id = shelter_id;
	}

	public String getShelterName() {
		return shelterName;
	}

	public void setShelterName(String shelterName) {
		this.shelterName = shelterName;
	}

	public ArrayList<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(ArrayList<Animal> animals) {
		this.animals = animals;
	}

	public boolean addIncomingAnimal(Animal animal) {
		return receiveAnimals ? animals.add(animal) : false;
	}
	
	public void enableReceivingAnimals() {
		receiveAnimals = true;
	}
	
	public void disableReceivingAnimals() {
		receiveAnimals = false;
	}
	public String getShelterId() {
		return shelter_id;
	}
	
	public boolean canReceiveAnimals() {
		return receiveAnimals;
	}

	@Override
	public String toString() {
		return animals.toString();
	}
	
	/**
	 *  Exports shelter data to a JSON formatted file
	 * 
	 */

	/**
	public void exportToJSON() {
		JSONArray animalList = new JSONArray();
		for(Animal animal : animals) { // loops through animal object and puts all the info
			JSONObject janimal = new JSONObject();
			janimal.put("animal_type", animal.getAnimal_type());
			janimal.put("animal_name", animal.getAnimal_name());
			janimal.put("animal_id", animal.getAnimal_id());
			janimal.put("weight", animal.getWeight());
			janimal.put("receipt_date", animal.getReceipt_date());
			
			animalList.add(janimal); // returns the updated data to the JSONObject list
		}
		//writes to file
		try (FileWriter file = new FileWriter("shelter" + shelter_id + ".json")) {
            JSONObject obj = new JSONObject();
            obj.put("shelter_"+shelter_id, animalList);
            file.write(obj.toJSONString()); 
            file.flush();
            System.out.println("Written to shelter" + shelter_id + ".json");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	*/
}
