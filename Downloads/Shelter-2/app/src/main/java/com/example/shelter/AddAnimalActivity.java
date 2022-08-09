package com.example.shelter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.shelter.model.Animal;
import com.example.shelter.model.Bird;
import com.example.shelter.model.Cat;
import com.example.shelter.model.Dog;
import com.example.shelter.model.Rabbit;
import com.example.shelter.model.Shelter;

import java.util.Date;
import java.util.List;

public class AddAnimalActivity extends AppCompatActivity {
    EditText animal_id;
    EditText animal_name;
    String animal_type;
    EditText weight;
    EditText unit;
    String shelterID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        Intent intent = getIntent();
        shelterID = intent.getStringExtra("shelterID");
    }

    public void radioButtonhandler(View view) {
        if (view instanceof RadioButton) {
            RadioButton radioButton = (RadioButton)view;
            boolean checked = radioButton.isChecked();
            System.out.println("checked radio" + radioButton.getId());
            System.out.println("dog radio" + R.id.dog_radio);
            System.out.println("Checked = "+ checked);

            switch (radioButton.getId()) {
                case R.id.dog_radio : {
                    if (checked) {
                        animal_type = "dog";
                    }
                    break;
                }
                case R.id.cat_radio : {
                    if (checked) {
                        animal_type = "cat";
                    }
                    break;
                }
                case R.id.rabbit_radio : {
                    if (checked) {
                        animal_type = "rabbit";
                    }
                    break;
                }
                case R.id.bird_radio : {
                    if (checked) {
                        animal_type = "bird";
                    }
                    break;
                }

            }
        }
    }

    public void submitbuttonHandler(View view) {
        animal_id = (EditText) findViewById(R.id.animal_id);
        String animalId = animal_id.getText().toString();

        animal_name = (EditText) findViewById(R.id.animal_name);
        String animalName = animal_name.getText().toString();

        weight = (EditText) findViewById(R.id.weight);
        String animalWeight = weight.getText().toString();
        Double animalWeightDouble = Double.parseDouble(animalWeight);

        unit = (EditText) findViewById(R.id.unit);
        String animalUnit = unit.getText().toString();

        Animal animal = createAnimal(animal_type);

        if(animal != null){
            animal.setAnimal_id(animalId);
            animal.setAnimal_type(animal_type);
            animal.setAnimal_name(animalName);
            animal.setShelter_id(shelterID);
            animal.setWeight(animalWeightDouble);
            animal.setUnit(animalUnit);
            animal.setReceipt_date(new Date().getTime());
            Shelter shelter = ((ShelterTrackerApplication) getApplication()).findShelterById(shelterID);
            if(shelter != null){
                shelter.addIncomingAnimal(animal);
            }

        }
        Intent detail = new Intent(getApplicationContext(), ShelterDetailsActivity.class);
        detail.putExtra("shelterID", shelterID);
        startActivity(detail);
    }

    private Animal createAnimal(String animal_type) {
        switch (animal_type){
            case "dog" : return new Dog();
            case "cat" : return new Cat();
            case "rabbit" : return new Rabbit();
            case "bird" : return new Bird();
            default:return null;
        }
    }
}