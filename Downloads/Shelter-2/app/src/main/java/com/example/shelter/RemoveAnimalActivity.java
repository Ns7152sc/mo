package com.example.shelter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shelter.model.Animal;
import com.example.shelter.model.Shelter;

public class RemoveAnimalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String shelterID;
    Shelter shelter;
    String[] data;
    String selectedAnimalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_animal);

        Intent intent = getIntent();
        shelterID = intent.getStringExtra("shelterID");

        shelter = ((ShelterTrackerApplication) getApplication()).findShelterById(shelterID);

        if(shelter != null) {
            data = entries(shelter);
        }

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,data);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    private String[] entries(Shelter shelter) {
        int num = shelter.getAnimals().size();
        int index = 0;
        String[] entries = new String[num];

        for (Animal animal : shelter.getAnimals()) {
            entries[index++] = animal.getAnimal_id();
        }
        return entries;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedAnimalID = data[position];
        Toast.makeText(getApplicationContext(),data[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void submitbuttonHandler(View view) {
        if(selectedAnimalID != null) {
            ((ShelterTrackerApplication) getApplication()).removeAnimal(shelterID, selectedAnimalID);
        }
        Intent detail = new Intent(getApplicationContext(), ShelterDetailsActivity.class);
        detail.putExtra("shelterID", shelter.getShelter_id());
        startActivity(detail);
    }
}