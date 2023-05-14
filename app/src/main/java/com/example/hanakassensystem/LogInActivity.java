package com.example.hanakassensystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hanakassensystem.AdminActivityKlassen.PersonalActivity;
import com.example.hanakassensystem.Klassen.Mitarbeiter;

import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {

    private ArrayList<Mitarbeiter>lstMitarbeiter;
    private EditText etMitarbeiterName, etMItarbeiterPasswort,etAdminName, etAdminPasswort;
    private int counter =0 ;
    private ConstraintLayout conAdmin, conMitarbeiter;
    private Integer mitarbeiterID = null;
    private database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        etMitarbeiterName = findViewById(R.id.etEmriPuntori);
        etMItarbeiterPasswort = findViewById(R.id.etPasswordPuntori);
        etAdminName = findViewById(R.id.etEmriAdmin);
        etAdminPasswort = findViewById(R.id.etAdminPasswort);
        Button btnAdminLogin = findViewById(R.id.btnLogInAdmin);
        Button btnMitarbeiterLogin = findViewById(R.id.btnLogInPuntori);
        conAdmin = findViewById(R.id.logInConAdmin);
        conMitarbeiter = findViewById(R.id.logInConPuntor);
        database = new database(this);

        lstMitarbeiter = database.getMitarbeiter("Select * from Mitarbeiter");


        btnMitarbeiterLogin.setOnClickListener(new View.OnClickListener() {
            private boolean passwortFalsch = true;
            String nameM;
            String passwortM;
            @Override
            public void onClick(View view) {

                for(int i = 1; i < lstMitarbeiter.size(); i++){
                    nameM  = lstMitarbeiter.get(i).getMitarbeiterName();
                    passwortM = lstMitarbeiter.get(i).getMitarbeiterPasswort();
                    String inputNameM = etMitarbeiterName.getText().toString();
                    String inputPasswortM = etMItarbeiterPasswort.getText().toString();
                    if(nameM.equals(inputNameM) && passwortM.equals(inputPasswortM)){
                        mitarbeiterID = lstMitarbeiter.get(i).getMitarbeiterID();
                        Intent intent1 = new Intent(LogInActivity.this,MainActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent1.putExtra("mitarbeiterID", mitarbeiterID);
                        database.insertArbeiterSchicht(mitarbeiterID);
                        startActivity(intent1);
                        Toast.makeText(LogInActivity.this, "Mir se vjen "+ nameM, Toast.LENGTH_SHORT).show();
                        passwortFalsch = false;
                        counter = 0;
                    }
                }
                if (passwortFalsch){
                    counter ++;
                    Toast.makeText(LogInActivity.this, "datat Gabimischt "+counter, Toast.LENGTH_SHORT).show();
                    etMitarbeiterName.setText("");
                    etMItarbeiterPasswort.setText("");
                }


            }
        });

        String name = lstMitarbeiter.get(0).getMitarbeiterName();
        String passwort = lstMitarbeiter.get(0).getMitarbeiterPasswort();
        btnAdminLogin.setOnClickListener(view -> {

            if((name.equals(etAdminName.getText().toString()))&&(passwort.equals(etAdminPasswort.getText().toString()))){
                Intent intent1 = new Intent(LogInActivity.this, PersonalActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent1);
            }else{
                counter ++;
                Toast.makeText(LogInActivity.this, "Gabimischt "+counter, Toast.LENGTH_SHORT).show();


            }

        });

        btnMitarbeiterLogin.setOnLongClickListener(view -> {

            conAdmin.setVisibility(View.VISIBLE);
            conMitarbeiter.setVisibility(View.GONE);
            return true;
        });

        btnAdminLogin.setOnLongClickListener(view -> {
            conAdmin.setVisibility(View.GONE);
            conMitarbeiter.setVisibility(View.VISIBLE);
            return true;
        });
    }
}