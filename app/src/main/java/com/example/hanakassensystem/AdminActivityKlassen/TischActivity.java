package com.example.hanakassensystem.AdminActivityKlassen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hanakassensystem.Adaptern.TischAdapter;
import com.example.hanakassensystem.Klassen.Tisch;
import com.example.hanakassensystem.R;
import com.example.hanakassensystem.database;

import java.util.ArrayList;

public class TischActivity extends AppCompatActivity {
    private ListView lvTische;
    private EditText etTischNummer;

    private database database;
    private ArrayList<Tisch> lstTisache;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tisch);
        Button btnAddTisch = findViewById(R.id.btnAddTavolina);
        Button btnDeletTisch = findViewById(R.id.btnDeleteTavolinen);
        Button btnUpdateTisch = findViewById(R.id.btnUpdatTavolin);
        Button btnKthehu = findViewById(R.id.btnBack2AdminTischActivity);
        lvTische = findViewById(R.id.lstTavolinatTischActivity);
        etTischNummer = findViewById(R.id.etTischNu);
        database = new database(this);
        refreshList();


        btnKthehu.setOnClickListener(view -> {
            Intent intent = new Intent(TischActivity.this, PersonalActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        btnAddTisch.setOnClickListener(view -> {
            if (!etTischNummer.getText().toString().equals("")) {
                database.insertTisch(Integer.valueOf(etTischNummer.getText().toString()));
                Toast.makeText(TischActivity.this, "e futja ni Numer Tavolinew", Toast.LENGTH_SHORT).show();
                refreshList();
                setNull();
            } else {
                Toast.makeText(TischActivity.this, "Mos e le that", Toast.LENGTH_SHORT).show();
            }
        });
        btnDeletTisch.setOnClickListener(view -> {
            database.deleteTisch(id);
            refreshList();
            setNull();
        });
        btnUpdateTisch.setOnClickListener(view -> {
            database.updateTisch(Integer.valueOf(etTischNummer.getText().toString()), id);
            refreshList();
            setNull();
        });
        lvTische.setOnItemClickListener((adapterView, view, i, l) -> {
            id = lstTisache.get(i).getTischID();
            int tischnr = lstTisache.get(i).getTischNummer();
            Toast.makeText(TischActivity.this, "Test "+tischnr, Toast.LENGTH_SHORT).show();
            etTischNummer.setText(String.valueOf(tischnr));
        });

    }
    public void refreshList() {
        String qry = "select * from tisch";
        lstTisache = database.getTisch(qry);
        if (!lstTisache.isEmpty()){
            TischAdapter tischAdapter = new TischAdapter(lstTisache, this);
            lvTische.setAdapter(tischAdapter);
        }
    }

    public void setNull() {
        etTischNummer.setText("");

    }

}