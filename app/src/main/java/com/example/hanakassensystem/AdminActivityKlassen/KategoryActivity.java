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

    private Button btnKategorieAdd, btnKategorieDelete, btnKategorieLoad, btnKategorieUpdate, btnExitKategorie,
    bt2Personal,btn2Produkt,btn2Statistik;
    private ListView lvKategorie;
    private EditText etKategorie;

    private database database;
    private ArrayList<Kategorie> lstKategorie;
    private KategorieAdapter kategorieAdapter;
    private int id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategory);
        btnKategorieUpdate = findViewById(R.id.btnUpdateKategorie);
        btnKategorieAdd = findViewById(R.id.btnAddKategorie);
        btnKategorieDelete = findViewById(R.id.btnDeleteKategorie);
        lvKategorie = findViewById(R.id.lstKategorie);
        etKategorie = findViewById(R.id.etKategorie);
        database = new database(this);
        etKategorie.setText("");
        btnExitKategorie = findViewById(R.id.btnExitKategory);

        btn2Produkt = findViewById(R.id.btnKategorie2Produkt);
        btn2Statistik = findViewById(R.id.btnKategorie2Statistik);
        bt2Personal = findViewById(R.id.btnKategorie2Personal);
        btn2Produkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KategoryActivity.this, ProduktActivity.class);
                finish();
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            }
        });
        btn2Statistik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KategoryActivity.this, StatistkActivity.class);
                finish();
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            }
        });

        bt2Personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KategoryActivity.this, PersonalActivity.class);
                finish();
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            }
        });

        btnExitKategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KategoryActivity.this, LogInActivity.class);
                finish();
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            }
        });
        refreshList();


        btnKategorieAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etKategorie.getText().toString().equals("")) {
                    database.insertKategorie(etKategorie.getText().toString());
                    Toast.makeText(KategoryActivity.this, "e fute ni kategorie", Toast.LENGTH_SHORT).show();
                    refreshList();
                    setNull();
                } else {
                    Toast.makeText(KategoryActivity.this, "Mos e le that", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnKategorieDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.deleteKategorie(id);
                refreshList();
                setNull();
            }
        });
        btnKategorieUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.updateKategorie(etKategorie.getText().toString(), id);
                refreshList();
                setNull();
            }
        });

        lvKategorie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                id = lstKategorie.get(i).getKategorieID();
                //Toast.makeText(getActivity(), "Test " +lstMitarbeiterArray.get(i).getMitarbeiterName(), Toast.LENGTH_SHORT).show();
                etKategorie.setText(lstKategorie.get(i).getKategorieTitel());
            }
        });

    }

    public void refreshList() {
        String qry = "select * from kategorie";
        lstKategorie = database.getKategorie(qry);
        kategorieAdapter = new KategorieAdapter(lstKategorie, this);
        lvKategorie.setAdapter((KategorieAdapter) kategorieAdapter);

    }

    public void setNull() {
        etKategorie.setText("");

    }
}