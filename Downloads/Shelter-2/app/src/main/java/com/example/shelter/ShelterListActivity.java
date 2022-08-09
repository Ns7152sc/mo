package com.example.shelter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shelter.model.Shelter;

public class ShelterListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);

        ListView lv = findViewById(R.id.shelter_list);

        lv.setAdapter(new ShelterAdapter(this,
                ((ShelterTrackerApplication) getApplication()).getShelterList()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Shelter shelter = ((ShelterTrackerApplication) getApplication()).getShelterList().get(position);
                Intent detail = new Intent(getApplicationContext(), ShelterDetailsActivity.class);
                detail.putExtra("shelterID", shelter.getShelter_id());
                startActivity(detail);

            }
        });

    }
}