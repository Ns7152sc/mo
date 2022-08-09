package com.example.shelter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shelter.model.Animal;
import com.example.shelter.model.Shelter;

import java.util.Date;

public class ShelterDetailsActivity extends AppCompatActivity {


    GridView gridView;
    GridView headerView;
    String[] data;
    String[] headerData;
    Button addAnimalButton;
    CheckBox enableReceive;
    String shelterID;
    Shelter shelter;
    boolean receiveAnimals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_details);

        Intent intent = getIntent();
        shelterID = intent.getStringExtra("shelterID");
        shelter = ((ShelterTrackerApplication) getApplication()).findShelterById(shelterID);
        //initiate a check box
        enableReceive = (CheckBox) findViewById(R.id.enableRecieve);
        if(shelter != null) {
            data = entries(shelter);
            receiveAnimals = shelter.canReceiveAnimals();

            enableReceive.setChecked(receiveAnimals);
        }
        //, "Unit", "Date Added"
        headerData = new String[]{
                "ID", "Name", "Type", "Weight"
        };

        gridView = findViewById(R.id.simpleGridView);
        headerView = findViewById(R.id.headerView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);

        gridView.setAdapter(adapter);

        ArrayAdapter<String> headerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, headerData);

        headerView.setAdapter(headerAdapter);




//        orderTotalField = findViewById(R.id.order_total);
//        orderTotalField.setText("Total: " + Cart.getCart_total());
//        clearButton = findViewById(R.id.clearCartButton);
//
//        clearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Cart.clear();
//                Intent cartPage = new Intent(getApplicationContext(), CartActivity.class);
//                startActivity(cartPage);
//            }
//        });
    }

    public String[] entries(Shelter shelter) {
        int num = 0;

        for (Animal animal : shelter.getAnimals()) {
            num += 4;
        }
        int index = 0;
        String[] entries = new String[num];

        for (Animal animal : shelter.getAnimals()) {
            entries[index++] = animal.getAnimal_id();
            entries[index++] = "" + animal.getAnimal_name();
            entries[index++] = "" + animal.getAnimal_type();
            entries[index++] = "" + animal.getWeight();
            //entries[index++] = "" + animal.getUnit();
            //entries[index++] = "" + new Date(animal.getReceipt_date()).toString();
        }
        return entries;
    }

    public void backbuttonHandler(View view) {
        //check current state of the check box=

            Intent back = new Intent(getApplicationContext(), ShelterListActivity.class);
            startActivity(back);
    }

    public void submitbuttonHandler(View view) {
        //check current state of the check box
        if(this.receiveAnimals){

        Intent detail = new Intent(getApplicationContext(), AddAnimalActivity.class);
        detail.putExtra("shelterID", shelter.getShelter_id());
        startActivity(detail);

        }
        else {
            Toast.makeText(this, "This shelter has disabled receiving animals.", Toast.LENGTH_LONG);
        }
    }

    public void deletebuttonHandler(View view) {
        //check current state of the check box
        if(shelter.getAnimals().size() > 0){

            Intent detail = new Intent(getApplicationContext(), RemoveAnimalActivity.class);
            detail.putExtra("shelterID", shelter.getShelter_id());
            startActivity(detail);

        }
        else {
            Toast.makeText(this, "This shelter has no animals.", Toast.LENGTH_LONG);
        }
    }

    public void checkBoxHandler(View view) {
        //check current state of the check box
        System.out.println("Checked");
        this.receiveAnimals = enableReceive.isChecked();
        if(this.receiveAnimals){
            System.out.println("Enabled");
            shelter.enableReceivingAnimals();
        }
        else {
            System.out.println("Disabled");
            shelter.disableReceivingAnimals();
        }
    }
}