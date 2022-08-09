package com.example.shelter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shelter.model.Shelter;

import java.util.List;

public class ShelterAdapter extends ArrayAdapter<Shelter> {
    public ShelterAdapter(Context context, List<Shelter> shelterList) {
        super(context, R.layout.shelter_list_item, shelterList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shelter_list_item, parent, false);
        }

        TextView id = convertView.findViewById(R.id.shelter_id);
        TextView name = convertView.findViewById(R.id.shelter_name);

        id.setText(getItem(position).getShelterId());
        name.setText(getItem(position).getShelterName());

        return convertView;
    }
}
