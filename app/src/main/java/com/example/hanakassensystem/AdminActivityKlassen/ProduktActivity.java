package com.example.hanakassensystem.AdminActivityKlassen;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hanakassensystem.Adaptern.ProduktAdapter;
import com.example.hanakassensystem.Klassen.Kategorie;
import com.example.hanakassensystem.Klassen.Produkt;
import com.example.hanakassensystem.LogInActivity;
import com.example.hanakassensystem.R;
import com.example.hanakassensystem.database;

import java.util.ArrayList;

public class ProduktActivity extends AppCompatActivity {

    EditText produktName, produktPreis;
    ArrayList<Kategorie> katList = new ArrayList<>();
    ArrayList<Produkt> proList;
    ListView lvProdukt;

    Button btnExitProdukt;

    // --Commented out by Inspection (14.05.2023 23:38):TextView textview;
    Dialog dialog;
    Integer id = null;
    int lvID;

    // --Commented out by I// --Commented out by Inspection (14.05.2023 23:38):nspection (14.05.2023 23:38):private int katID;
    private int prodID;
    database database;
    Button bt2Kategorie, btn2Personal, btn2Statistik;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produkt);

        produktName = findViewById(R.id.etProduktEmriFragment);
        produktPreis = findViewById(R.id.etProduktQmimiFragment);
        Button btnAddProfuktFragment = findViewById(R.id.btnAddProduktFragment);
        Button btnDeleteProfuktFragment = findViewById(R.id.btnDeleteProduktFragment);
        Button btnUpdateProfuktFragment = findViewById(R.id.btnUpdateProduktFragment);
        lvProdukt = findViewById(R.id.lvProduktFragment);
        database = new database(this);
        TextView textview = findViewById(R.id.testView);
        btnExitProdukt = findViewById(R.id.btnExitProdukt);
        bt2Kategorie = findViewById(R.id.btnProdukt2Kategorie);
        btn2Personal = findViewById(R.id.btnProdukt2Personal);
        btn2Statistik = findViewById(R.id.btnProdukt2Statistik);

        bt2Kategorie.setOnClickListener(view -> {
            Intent intent = new Intent(ProduktActivity.this, KategoryActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        btn2Personal.setOnClickListener(view -> {
            Intent intent = new Intent(ProduktActivity.this, PersonalActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        btn2Statistik.setOnClickListener(view -> {
            Intent intent = new Intent(ProduktActivity.this, StatistkActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        btnExitProdukt.setOnClickListener(view -> {
            Intent intent = new Intent(ProduktActivity.this, LogInActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });

        /////////////////////////////////////////////////////////////////////////////////


        String qry = "select * from kategorie";
        katList = database.getKategorie(qry);
        ArrayList<String> stringList = new ArrayList<>();
        ArrayList<Integer> intListID = new ArrayList<>();

        int size = katList.size();
        for (int i = 0; i < size; i++) {
            stringList.add(katList.get(i).getKategorieTitel());
            intListID.add(katList.get(i).getKategorieID());
        }
        textview.setOnClickListener(v -> {
            dialog = new Dialog(ProduktActivity.this);
            dialog.setContentView(R.layout.dialog_searchable_spinner);
            dialog.getWindow().setLayout(650, 800);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            EditText editText = dialog.findViewById(R.id.edit_text);
            ListView listView = dialog.findViewById(R.id.list_view);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(ProduktActivity.this, android.R.layout.simple_list_item_1, stringList);
            listView.setAdapter(adapter);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            listView.setOnItemClickListener((parent, view, position, ids) -> {
                id = intListID.get(position);
                sqlSelect(id);
                textview.setText(adapter.getItem(position));
                dialog.dismiss();
            });

        });
        lvProdukt.setOnItemClickListener((adapterView, view, i, l) -> {

            lvID = proList.get(i).getProduktID();
            produktName.setText(proList.get(i).getProduktName());
            produktPreis.setText(String.valueOf(proList.get(i).getProduktPreis()));
        });
        btnDeleteProfuktFragment.setOnClickListener(view -> {
            if (produktName.getText().toString().trim().length() > 0 && produktPreis.getText().toString().trim().length() > 0) {
                database.deleteProdukt(lvID);
                Toast.makeText(ProduktActivity.this, "Delete OK!", Toast.LENGTH_SHORT).show();
                setNull();
                sqlSelect(id);
            } else {
                Toast.makeText(ProduktActivity.this, "Zgidhe ni produkt me shly", Toast.LENGTH_SHORT).show();
            }

        });
        btnUpdateProfuktFragment.setOnClickListener(view -> {
            if (produktName.getText().toString().trim().length() > 0 && produktPreis.getText().toString().trim().length() > 0) {
                double preis = Double.parseDouble(produktPreis.getText().toString());
                String name = produktName.getText().toString();
                database.updateProdukt(name, preis, lvID);
                Toast.makeText(ProduktActivity.this, "Save!", Toast.LENGTH_SHORT).show();
                setNull();
                sqlSelect(id);
                //textview.setText("");
            } else {
                Toast.makeText(ProduktActivity.this, "Mos e le That", Toast.LENGTH_SHORT).show();
            }
        });
        btnAddProfuktFragment.setOnClickListener(view -> {
            if (produktName.getText().toString().trim().length() > 0 && produktPreis.getText().toString().trim().length() > 0 && id != null) {
                double preis = Double.parseDouble(produktPreis.getText().toString());
                String name = produktName.getText().toString();
                database.insertProdukt(name, preis, id);
                Toast.makeText(ProduktActivity.this, "Save!", Toast.LENGTH_SHORT).show();
                setNull();
                sqlSelect(id);
            } else {
                Toast.makeText(ProduktActivity.this, "Mos e le That", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void sqlSelect(int id) {
        String qry = "Select * from produkt where p_kategorieID =" + id;
        proList = database.getProdukt(qry);
        if (!proList.isEmpty()) {
            ProduktAdapter produktAdapter = new ProduktAdapter(proList, this);
            lvProdukt.setAdapter(produktAdapter);
        }
    }

    public void setNull() {
        produktName.setText("");
        produktPreis.setText("");
    }
}