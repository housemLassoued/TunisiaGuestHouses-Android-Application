package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AdminPageActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private RecyclerView recyclerView;
    private GuestAdapter guestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fab = findViewById(R.id.fab);
        Button allVisitorButton = findViewById(R.id.allVisitorButton);

        dbHelper = new DatabaseHelper(this);

        // Configurer le RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        guestAdapter = new GuestAdapter(this,new ArrayList<>(),true);
        recyclerView.setAdapter(guestAdapter);

        // Charger les invités dans le RecyclerView
        loadGuests();

        // Ajouter un nouvel invité
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPageActivity.this, AddGuestActivity.class);
                startActivity(intent);
            }
        });
        allVisitorButton.setOnClickListener(v -> {
            Toast.makeText(this, "Navigating to Main Page", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AdminPageActivity.this,MainActivity.class);
            startActivity(intent);
        });
        guestAdapter.setOnItemClickListener(new GuestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Guest guest) {
                // Naviguer vers AdminUpdateDeletePage avec les détails de l'invité
                Intent intent = new Intent(AdminPageActivity.this, AdminUpdateDeletePage.class);
                intent.putExtra("guest_id", guest.getId());
                intent.putExtra("guest_name", guest.getName());
                intent.putExtra("guest_region", guest.getRegion());
                intent.putExtra("guest_city", guest.getCity());
                intent.putExtra("guest_price", guest.getPrice());
                intent.putExtra("guest_image", guest.getImage());
                startActivity(intent);
            }
        });

    }

    private void loadGuests() {
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

    @Override
    protected void onResume() {
        super.onResume();
        // Recharger les invités après retour à cette activité
        loadGuests();
    }
}
