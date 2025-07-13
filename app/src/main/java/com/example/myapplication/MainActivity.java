
package com.example.myapplication;

import static android.os.Build.VERSION_CODES.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.myapplication.R.layout.activity_main);
        Button allVisitorButton = findViewById(com.example.myapplication.R.id.allVisitorButton);
        Button justForAdminButton = findViewById(com.example.myapplication.R.id.justForAdminButton);
        allVisitorButton.setOnClickListener(v -> {
            Toast.makeText(this, "Navigating to Visitor Page", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, VisitorPageActivity.class);
            startActivity(intent);
        });
        justForAdminButton.setOnClickListener(v -> showAdminDialog());

    }
    private void showAdminDialog() {
        // Créer une vue à partir du layout du dialog
        View dialogView = LayoutInflater.from(this).inflate(com.example.myapplication.R.layout.activity_main_check_admin,null);

        // Trouver les vues dans le dialog
        EditText guestId = dialogView.findViewById(com.example.myapplication.R.id.dialog_guest_id);
        EditText guestName = dialogView.findViewById(com.example.myapplication.R.id.dialog_guest_name);
        Button cancelButton = dialogView.findViewById(com.example.myapplication.R.id.dialog_cancel_button);
        Button submitButton = dialogView.findViewById(com.example.myapplication.R.id.dialog_submit_button);

        // Créer et afficher le dialog
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        cancelButton.setOnClickListener(v -> alertDialog.dismiss());

        submitButton.setOnClickListener(v -> {
            String id = guestId.getText().toString();
            String password = guestName.getText().toString();

            if ("admin".equals(id) && "password123".equals(password)) {
                alertDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, AdminPageActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
    }
}
