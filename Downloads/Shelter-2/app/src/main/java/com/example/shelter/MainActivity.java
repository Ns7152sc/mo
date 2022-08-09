package com.example.shelter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.shelter.model.Animal;
import com.example.shelter.model.Shelter;
import com.example.shelter.utils.InputDTO;
import com.example.shelter.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "Project1_input.json");
        Log.i("data", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<InputDTO>() { }.getType();
        InputDTO dto = gson.fromJson(jsonFileString, listUserType);
        List<Animal> animals = dto.getShelter_roster();
        // remove shelter.ser and replace with program input
        // load into application class
        ShelterTrackerApplication application = ((ShelterTrackerApplication) getApplication());
        application.addAnimals(animals);
//        for (int i = 0; i < animals.size(); i++) {
//            if(animals.get(i) != null) {
//                Log.i("data", "> Item " + i + "\n" + animals.get(i));
//                //application.addShelter(shelters.get(i));
//                //application.addAnimalToAppropriateShelter(shelters.get(i));
//            }
//        }
    }



    public void showShelterList(View view) {
        Intent intent = new Intent(this, ShelterListActivity.class);
        startActivity(intent);
    }
}