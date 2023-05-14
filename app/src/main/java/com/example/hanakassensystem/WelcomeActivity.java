package com.example.hanakassensystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hanakassensystem.Klassen.Mitarbeiter;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    ArrayList<Mitarbeiter> lstMitarbeiter;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        db = new database(this);
        lstMitarbeiter = db.getMitarbeiter("Select * from Mitarbeiter");

        if (lstMitarbeiter.isEmpty()) {

            AlertDialog.Builder alert = new AlertDialog.Builder(WelcomeActivity.this);
            alert.setTitle("            Jepja Admin Passwort");
            LinearLayout layout = new LinearLayout(WelcomeActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);

            final EditText adminName = new EditText(WelcomeActivity.this);
            adminName.setInputType(InputType.TYPE_CLASS_TEXT);
            adminName.setMaxLines(1);
            adminName.setHint("Emri");
            adminName.setGravity(Gravity.CENTER);
            layout.addView(adminName);

            final EditText adminPasswort = new EditText(WelcomeActivity.this);
            adminPasswort.setInputType(InputType.TYPE_CLASS_TEXT);
            adminPasswort.setMaxLines(1);
            adminPasswort.setHint("*********");
            adminPasswort.setGravity(Gravity.CENTER);
            layout.addView(adminPasswort);

            alert.setView(layout);

            alert.setPositiveButton("Save", (dialog, whichButton) -> {
                String name = adminName.getText().toString();
                String passwort = adminPasswort.getText().toString();
                if (!adminName.getText().toString().equals("") && !adminPasswort.getText().toString().equals("")){
                    db.insertMitarbeiter(name, passwort);
                    Toast.makeText(WelcomeActivity.this, "Regjistrimi OK", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(WelcomeActivity.this, LogInActivity.class);
                    finish();
                    startActivity(intent);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                }else{
                    Toast.makeText(WelcomeActivity.this, "Sbon me kan That", Toast.LENGTH_SHORT).show();
                }


            });

            alert.setNegativeButton("Cancel", (dialog, whichButton) -> {
                // what ever you want to do with No option.
            });

            alert.show();
        }
        else {

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent intent = new Intent(WelcomeActivity.this, LogInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);


            }, 2000);
        }
    }
}