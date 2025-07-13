package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VisitorPageActivity extends AppCompatActivity {

    Spinner regionSpinner, citySpinner, priceSpinner;
    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    private GuestAdapter guestAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visitor_page);

        dbHelper = new DatabaseHelper(this);
        regionSpinner = findViewById(R.id.regionSpinner);
        citySpinner = findViewById(R.id.citySpinner);
        priceSpinner = findViewById(R.id.priceSpinner);
        recyclerView = findViewById(R.id.recyclerView);
        Button allVisitorButton = findViewById(R.id.allVisitorButton);

        ArrayAdapter<CharSequence> regionAdapter = ArrayAdapter.createFromResource(this, R.array.regions_array, android.R.layout.simple_spinner_item);
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionSpinner.setAdapter(regionAdapter);

        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);

        ArrayAdapter<CharSequence> priceAdapter = ArrayAdapter.createFromResource(this, R.array.classes, android.R.layout.simple_spinner_item);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceSpinner.setAdapter(priceAdapter);

        // Configurer RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        guestAdapter = new GuestAdapter(this, new ArrayList<>(),false);
        recyclerView.setAdapter(guestAdapter);

        allVisitorButton.setOnClickListener(v -> {
            Toast.makeText(this, "Navigating to Main Page", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(VisitorPageActivity.this,MainActivity.class);
            startActivity(intent);
        });

        // Listener pour les spinners
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateRecyclerView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };

        regionSpinner.setOnItemSelectedListener(spinnerListener);
        citySpinner.setOnItemSelectedListener(spinnerListener);
        priceSpinner.setOnItemSelectedListener(spinnerListener);

        // Charger les invités initialement
        loadGuests();
    }

    private void loadGuests() {
        // Charger tous les invités sans filtre
        List<Guest> guestList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("guests", null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String region = cursor.getString(cursor.getColumnIndexOrThrow("region"));
                String city = cursor.getString(cursor.getColumnIndexOrThrow("city"));
                String price = cursor.getString(cursor.getColumnIndexOrThrow("price"));
                String image = cursor.getString(cursor.getColumnIndexOrThrow("image"));

                guestList.add(new Guest(id, name, region, city, price, image));
            }
            cursor.close();
        }

        guestAdapter.updateGuests(guestList);
    }

    private void updateRecyclerView() {
        String selectedRegion = regionSpinner.getSelectedItem().toString();
        String selectedCity = citySpinner.getSelectedItem().toString();
        String selectedPrice = priceSpinner.getSelectedItem().toString();

        // Convertir la sélection de prix en plage SQL
        String priceRange;
        switch (selectedPrice) {
            case "Budget-Friendly (30/50 TND per night)":
                priceRange = "price BETWEEN 30 AND 50";
                break;
            case "Mid-Range(80/150 TND per night)":
                priceRange = "price BETWEEN 80 AND 150";
                break;
            case "Luxury(minimum 200 per night)":
                priceRange = "price >= 200";
                break;
            case "Boutique Guest House(100/200 TND per night)":
                priceRange = "price BETWEEN 100 AND 200";
                break;
            case "Prestige(minimum 500 TND per night)":
                priceRange = "price >= 500";
                break;
            default:
                priceRange = "1=1"; // Pas de filtre sur le prix
                break;
        }

        // Requête avec filtres
        Cursor cursor = dbHelper.getFilteredGuests(
                selectedRegion.equals("All") ? "%" : selectedRegion,
                selectedCity.equals("All") ? "%" : selectedCity,
                priceRange
        );

        List<Guest> guests = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String region = cursor.getString(cursor.getColumnIndexOrThrow("region"));
                String city = cursor.getString(cursor.getColumnIndexOrThrow("city"));
                String price = cursor.getString(cursor.getColumnIndexOrThrow("price"));
                String image = cursor.getString(cursor.getColumnIndexOrThrow("image"));

                guests.add(new Guest(id, name, region, city, price, image));
            }
            cursor.close();
        }

        guestAdapter.updateGuests(guests);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recharger tous les invités à chaque retour sur cette activité
        loadGuests();
    }
}
