package com.example.shelter;

import android.app.Application;
import android.util.Log;

import com.example.shelter.model.Animal;
import com.example.shelter.model.Shelter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShelterTrackerApplication extends Application {

    private HashMap<String, Shelter> shelters = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
//        for(int i = 0; i < 20; i++) {
//            shelterList.add(new Shelter(Integer.toString(i), "Shelter Number " + i));
//        }
    }

    public void addAnimals(List<Animal> animals){
        Log.i("controller", "Adding animals");
        // do something
        for (Animal animal : animals) {
            Shelter shelter = shelters.get(animal.getShelter_id());
            if(shelter  == null){
                shelter = new Shelter(animal.getShelter_id(),  animal.getShelter_id());
            }
            shelter.addIncomingAnimal(animal);
            shelters.put(animal.getShelter_id(), shelter);
            Log.i("controller", animal.getShelter_id() + " " + shelter.getAnimals().size());
        }
    }


    public Shelter getShelter(String shelter_id) {
        return shelters.get(shelter_id);
    }

    public List<Shelter> getShelterList() {
        List<Shelter> shelterList = new ArrayList<>();
        for (Map.Entry<String, Shelter> entry : shelters.entrySet()) {
            shelterList.add(entry.getValue());
        }
        return shelterList;
    }



    public Shelter findShelterById(String shelterID){
        return shelters.get(shelterID);
    }

    public void removeAnimal(String shelterID, String animalID){
        Shelter shelter = shelters.get(shelterID);
        if(shelter != null){
            Iterator<Animal> animalIterator = shelter.getAnimals().iterator();
            while (animalIterator.hasNext()){
                Animal animal = animalIterator.next();
                if(animal.getAnimal_id().equals(animalID)){
                    animalIterator.remove();
                    break;
                }
            }
        }
    }

}
