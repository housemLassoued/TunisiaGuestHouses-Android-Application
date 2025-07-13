package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AdminUpdateDeletePage extends AppCompatActivity {

    private EditText nameEditText, regionEditText, cityEditText, priceEditText, guestImageView, guestIdn;
    private Button updateButton, deleteButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_update_dialog);

        // Initialiser les vues
        guestIdn = findViewById(R.id.guestId);
        nameEditText = findViewById(R.id.nameEditText);
        regionEditText = findViewById(R.id.regionEditText);
        cityEditText = findViewById(R.id.cityEditText);
        priceEditText = findViewById(R.id.priceEditText);
        guestImageView = findViewById(R.id.guestImageView);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        // Initialiser la base de données
        dbHelper = new DatabaseHelper(this);

        // Récupérer les détails de l'invité
        Intent intent = getIntent();
        int guestId = intent.getIntExtra("guest_id", -1);
        String guestName = intent.getStringExtra("guest_name");
        String guestRegion = intent.getStringExtra("guest_region");
        String guestCity = intent.getStringExtra("guest_city");
        String guestPrice = intent.getStringExtra("guest_price");
        String guestImage = intent.getStringExtra("guest_image");

        // Afficher les détails dans les champs
        guestIdn.setText(String.valueOf(guestId));
        nameEditText.setText(guestName);
        regionEditText.setText(guestRegion);
        cityEditText.setText(guestCity);
        priceEditText.setText(guestPrice);
        guestImageView.setText(guestImage);

        // Bouton de mise à jour
        updateButton.setOnClickListener(v -> {
            updateGuest(guestId);
        });

        // Bouton de suppression
        deleteButton.setOnClickListener(v -> {
            deleteGuest(guestId);
        });
    }

    // Méthode pour mettre à jour l'invité dans la base de données
    private void updateGuest(int guestId) {
        String name = nameEditText.getText().toString().trim();
        String region = regionEditText.getText().toString().trim();
        String city = cityEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();
        String image = guestImageView.getText().toString().trim();

        if (name.isEmpty() || region.isEmpty() || city.isEmpty() || price.isEmpty() || image.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mettre à jour les informations dans la base de données
        boolean isUpdated = dbHelper.updateGuest(guestId, name, region, city, price, image);
        if (isUpdated) {
            Toast.makeText(this, "Invité mis à jour avec succès.", Toast.LENGTH_SHORT).show();

            // Naviguer vers AdminPageActivity après mise à jour réussie
            Intent intent = new Intent(AdminUpdateDeletePage.this, AdminPageActivity.class);
            startActivity(intent); // Démarrer AdminPageActivity
            finish(); // Terminer l'activité actuelle
        } else {
            Toast.makeText(this, "Échec de la mise à jour.", Toast.LENGTH_SHORT).show();
        }
    }

    // Méthode pour supprimer l'invité de la base de données
    private void deleteGuest(int guestId) {
        boolean isDeleted = dbHelper.deleteGuest(guestId);
        if (isDeleted) {
            Toast.makeText(this, "Invité supprimé avec succès.", Toast.LENGTH_SHORT).show();

            // Naviguer vers AdminPageActivity après suppression réussie
            Intent intent = new Intent(AdminUpdateDeletePage.this, AdminPageActivity.class);
            startActivity(intent); // Démarrer AdminPageActivity
            finish(); // Terminer l'activité actuelle
        } else {
            Toast.makeText(this, "Échec de la suppression.", Toast.LENGTH_SHORT).show();
        }
    }

}
