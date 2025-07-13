package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddGuestActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_activity);

        // Initialisation des champs d'entrée
        EditText name = findViewById(R.id.EdN);
        EditText region = findViewById(R.id.EdR);
        EditText city = findViewById(R.id.EdC);
        EditText price = findViewById(R.id.Edprice);
        EditText image = findViewById(R.id.Edim);
        Button button = findViewById(R.id.allVisitorButton);

        // Initialisation de l'aide pour la base de données
        dbHelper = new DatabaseHelper(this);

        // Gestion du clic sur le bouton
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs saisies
                String guestName = name.getText().toString().trim();
                String guestRegion = region.getText().toString().trim();
                String guestCity = city.getText().toString().trim();
                String guestPrice = price.getText().toString().trim();
                String guestImage = image.getText().toString().trim();

                // Vérification des champs obligatoires
                if (guestName.isEmpty() || guestRegion.isEmpty() || guestCity.isEmpty() || guestPrice.isEmpty() || guestImage.isEmpty()) {
                    Toast.makeText(AddGuestActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Ajouter les données à la base de données
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", guestName);
                values.put("region", guestRegion);
                values.put("city", guestCity);
                values.put("price", guestPrice);
                values.put("image", guestImage);

                long newRowId = db.insert("guests", null, values);
                if (newRowId != -1) {
                    Toast.makeText(AddGuestActivity.this, "Invité ajouté avec succès !", Toast.LENGTH_SHORT).show();

                    // Retourner à la page AdminPageActivity
                    Intent intent = new Intent(AddGuestActivity.this, AdminPageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AddGuestActivity.this, "Erreur lors de l'ajout de l'invité.", Toast.LENGTH_SHORT).show();
                }

                db.close();
            }
        });
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}

