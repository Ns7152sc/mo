package com.example.shelter.utils;

import com.example.shelter.model.Animal;
import com.example.shelter.model.Shelter;

import java.util.List;

public class InputDTO {
    List<Animal> shelter_roster;

    public List<Animal> getShelter_roster() {
        return shelter_roster;
    }

    public void setShelter_roster(List<Animal> shelter_roster) {
        this.shelter_roster = shelter_roster;
    }
}
