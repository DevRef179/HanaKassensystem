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

import com.example.hanakassensystem.Adaptern.KategorieAdapter;
import com.example.hanakassensystem.Klassen.Kategorie;
import com.example.hanakassensystem.LogInActivity;
import com.example.hanakassensystem.R;
import com.example.hanakassensystem.database;

import java.util.ArrayList;

public class KategoryActivity extends AppCompatActivity {
    private ListView lvKategorie;
    private EditText etKategorie;

    private database database;
    private ArrayList<Kategorie> lstKategorie;
    private int id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategory);
        Button btnKategorieUpdate = findViewById(R.id.btnUpdateKategorie);
        Button btnKategorieAdd = findViewById(R.id.btnAddKategorie);
        Button btnKategorieDelete = findViewById(R.id.btnDeleteKategorie);
        lvKategorie = findViewById(R.id.lstKategorie);
        etKategorie = findViewById(R.id.etKategorie);
        database = new database(this);
        etKategorie.setText("");
        Button btnExitKategorie = findViewById(R.id.btnExitKategory);

        Button btn2Produkt = findViewById(R.id.btnKategorie2Produkt);
        Button btn2Statistik = findViewById(R.id.btnKategorie2Statistik);
        Button bt2Personal = findViewById(R.id.btnKategorie2Personal);
        btn2Produkt.setOnClickListener(view -> {
            Intent intent = new Intent(KategoryActivity.this, ProduktActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        btn2Statistik.setOnClickListener(view -> {
            Intent intent = new Intent(KategoryActivity.this, StatistkActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });

        bt2Personal.setOnClickListener(view -> {
            Intent intent = new Intent(KategoryActivity.this, PersonalActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });

        btnExitKategorie.setOnClickListener(view -> {
            Intent intent = new Intent(KategoryActivity.this, LogInActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        refreshList();


        btnKategorieAdd.setOnClickListener(view -> {
            if (!etKategorie.getText().toString().equals("")) {
                database.insertKategorie(etKategorie.getText().toString());
                Toast.makeText(KategoryActivity.this, "e fute ni kategorie", Toast.LENGTH_SHORT).show();
                refreshList();
                setNull();
            } else {
                Toast.makeText(KategoryActivity.this, "Mos e le that", Toast.LENGTH_SHORT).show();
            }
        });
        btnKategorieDelete.setOnClickListener(view -> {
            database.deleteKategorie(id);
            refreshList();
            setNull();
        });
        btnKategorieUpdate.setOnClickListener(view -> {
            database.updateKategorie(etKategorie.getText().toString(), id);
            refreshList();
            setNull();
        });

        lvKategorie.setOnItemClickListener((adapterView, view, i, l) -> {
            id = lstKategorie.get(i).getKategorieID();
            //Toast.makeText(getActivity(), "Test " +lstMitarbeiterArray.get(i).getMitarbeiterName(), Toast.LENGTH_SHORT).show();
            etKategorie.setText(lstKategorie.get(i).getKategorieTitel());
        });

    }

    public void refreshList() {
        String qry = "select * from kategorie";
        lstKategorie = database.getKategorie(qry);
        KategorieAdapter kategorieAdapter = new KategorieAdapter(lstKategorie, this);
        lvKategorie.setAdapter(kategorieAdapter);

    }

    public void setNull() {
        etKategorie.setText("");

    }
}