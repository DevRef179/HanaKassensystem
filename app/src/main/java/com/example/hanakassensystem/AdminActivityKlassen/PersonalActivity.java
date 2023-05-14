package com.example.hanakassensystem.AdminActivityKlassen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hanakassensystem.Adaptern.MitarbeiterAdapter;
import com.example.hanakassensystem.Klassen.Mitarbeiter;
import com.example.hanakassensystem.LogInActivity;
import com.example.hanakassensystem.R;
import com.example.hanakassensystem.database;

import java.util.ArrayList;

public class PersonalActivity extends AppCompatActivity {

    private EditText etEmriPuntoriFragment, etPasswortPuntoriFragment;
    private Button btnAddPuntorFragment;
    private Button btnUpdatePuntorFragment;
    private Button btnDeletePuntorFragment;
    private Button btnLoadPuntorFragment;
    private database database;

    // --Commented out by Inspection (14.05.2023 23:38):private ConstraintLayout con;
    private ArrayList<Mitarbeiter> lstMitarbeiterArray;
    private int id;
    private ListView lvPuntori;
    Button bt2Kategorie,btn2Produkt,btn2Statistik, btn2Tisch;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        btnAddPuntorFragment = findViewById(R.id.btnAddPuntorFragment);
        btnUpdatePuntorFragment = findViewById(R.id.btnUpdatePuntorFragment);
        btnDeletePuntorFragment = findViewById(R.id.btnDeletePuntorFragment);
        btnLoadPuntorFragment = findViewById(R.id.btnReloadPuntorFragment);
        lvPuntori = findViewById(R.id.lstPuntortFragment);
        database = new database(this);
        etEmriPuntoriFragment = findViewById(R.id.etEmriPuntoriFragment);
        etPasswortPuntoriFragment =  findViewById(R.id.etPasswortPuntoriFragment);
        Button btnExitPersonal = findViewById(R.id.btnExitPersonal);

        btn2Tisch = findViewById(R.id.btnTischPersonal);
        bt2Kategorie = findViewById(R.id.btnPersonal2Kategorie);
        btn2Produkt = findViewById(R.id.btnPersonal2Produkt);
        btn2Statistik = findViewById(R.id.btnPersonal2Statistik);


        btn2Tisch.setOnClickListener(view -> {
            Intent intent = new Intent(PersonalActivity.this, TischActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        bt2Kategorie.setOnClickListener(view -> {
            Intent intent = new Intent(PersonalActivity.this, KategoryActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        btn2Produkt.setOnClickListener(view -> {
            Intent intent = new Intent(PersonalActivity.this, ProduktActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        btn2Statistik.setOnClickListener(view -> {
            Intent intent = new Intent(PersonalActivity.this, StatistkActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        btnExitPersonal.setOnClickListener(view -> {
            Intent intent = new Intent(PersonalActivity.this, LogInActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });

        refreshList();
        btnLoadPuntorFragment.setOnClickListener(view -> {
            clickLoad();
            refreshList();
            setNull();
        });
        lvPuntori.setOnItemClickListener((adapterView, view, i, l) -> {
            id = lstMitarbeiterArray.get(i).getMitarbeiterID();
            //Toast.makeText(getActivity(), "Test " +lstMitarbeiterArray.get(i).getMitarbeiterName(), Toast.LENGTH_SHORT).show();
            etEmriPuntoriFragment.setText(lstMitarbeiterArray.get(i).getMitarbeiterName());
            etPasswortPuntoriFragment.setText(lstMitarbeiterArray.get(i).getMitarbeiterPasswort());
            clickList();
        });
        btnDeletePuntorFragment.setOnClickListener(view -> {
            database.deleteMitarbeiter(id);
            refreshList();
            setNull();
        });
        btnUpdatePuntorFragment.setOnClickListener(view -> {
            database.updateMitarbeiter(etEmriPuntoriFragment.getText().toString(), etPasswortPuntoriFragment.getText().toString(), id);
            refreshList();
            setNull();
        });
        btnAddPuntorFragment.setOnClickListener(view -> {
            if (!etEmriPuntoriFragment.getText().toString().equals("") && !etPasswortPuntoriFragment.getText().toString().equals("")) {
                database.insertMitarbeiter(etEmriPuntoriFragment.getText().toString(), etPasswortPuntoriFragment.getText().toString());
                Toast.makeText(PersonalActivity.this, "e fute", Toast.LENGTH_SHORT).show();
                refreshList();
            } else {
                Toast.makeText(PersonalActivity.this, "Mos le that", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void refreshList() {
        String qry = "select * from mitarbeiter";
        lstMitarbeiterArray = database.getMitarbeiter(qry);
        MitarbeiterAdapter mitarbeiterAdapter = new MitarbeiterAdapter(lstMitarbeiterArray, this);
        lvPuntori.setAdapter(mitarbeiterAdapter);

    }

    public void setNull() {
        etEmriPuntoriFragment.setText("");
        etPasswortPuntoriFragment.setText("");
    }

    public void clickList() {
        btnAddPuntorFragment.setVisibility(View.GONE);
        btnDeletePuntorFragment.setVisibility(View.VISIBLE);
        btnUpdatePuntorFragment.setVisibility(View.VISIBLE);
        btnLoadPuntorFragment.setVisibility(View.VISIBLE);
    }

    public void clickLoad() {
        btnAddPuntorFragment.setVisibility(View.VISIBLE);
        btnDeletePuntorFragment.setVisibility(View.GONE);
        btnUpdatePuntorFragment.setVisibility(View.GONE);
        btnLoadPuntorFragment.setVisibility(View.GONE);
    }
}