package com.example.hanakassensystem.AdminActivityKlassen;

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
import com.example.hanakassensystem.Klassen.Mitarbeiter;
import com.example.hanakassensystem.Klassen.Produkt;
import com.example.hanakassensystem.Klassen.Tisch;
import com.example.hanakassensystem.LogInActivity;
import com.example.hanakassensystem.MainActivity;
import com.example.hanakassensystem.R;
import com.example.hanakassensystem.database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BestellungsActivitys extends AppCompatActivity {
    ArrayList<Kategorie> katList = new ArrayList<>();
    ArrayList<Produkt> prodList = new ArrayList<>();
    ArrayList<Tisch> tischList = new ArrayList<>();
    ArrayList<Mitarbeiter> mitList = new ArrayList<>();


    ArrayAdapter<String> adapter1;

    private String produktName;


    private int b_produktID;
    private String produktname;
    private double b_produktPreis;
    private int b_tischID;


    Dialog dialog;
    int tischID;
    Integer tischNr = null;
    int lvID;
    private database database;
    private Integer katID = null;
    private Integer prodID = null;


    private TextView txtKategorie, txtProdukt, txtTischID;
    private FloatingActionButton fabCountPos, fabBooking;
    private EditText etProduktAnzahl;
    private Integer mitarbeiterID = null;

    private int produktAnzahl = 1;
    ListView listView1;

    ArrayList<String> stringListProd = new ArrayList<>();
    ArrayList<Integer> intListProd = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestellungs_activitys);
        txtProdukt = findViewById(R.id.testViewProdukt);
        txtKategorie = findViewById(R.id.testViewKategorie);
        fabCountPos = findViewById(R.id.fabCountPos);
        fabBooking = findViewById(R.id.fabBooking);
        etProduktAnzahl = findViewById(R.id.etAnzahlProdukt);
        txtTischID = findViewById(R.id.txtTavolinaNrBestellungsActivity);
        database = new database(this);


        Intent intent = getIntent();
        tischID = intent.getIntExtra("tisch", 0);
        String qry = "select * from Tisch where tischid =" + tischID;
        tischList = database.getTisch(qry);

        mitarbeiterID = intent.getIntExtra("mitarbeiterID", 0);
        tischNr = tischList.get(0).getTischNummer();
        txtTischID.setText(tischNr.toString());


        //produktName = prodList.get()
        fabCountPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produktAnzahl++;
                etProduktAnzahl.setText(String.valueOf(produktAnzahl));
            }
        });
        fabBooking.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(BestellungsActivitys.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("mitarbeiterID", mitarbeiterID);
                startActivity(intent);

                return true;
            }
        });
        fabBooking.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (katID != null && prodID != null) {
                    if (tischID == 0) {
                        tischID = 0;
                    } else {
                        Toast.makeText(BestellungsActivitys.this, "Save !!" + prodID, Toast.LENGTH_SHORT).show();
                        database.insertBestellung(b_produktID,tischID,produktName,b_produktPreis,produktAnzahl,mitarbeiterID);
                        database.updateTischTisch(1,tischID);
                        produktAnzahl = 1;
                        etProduktAnzahl.setText(String.valueOf(1));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);

                    }
                }
            }
        });

        /////////////////////////////////////////////////////////////////////


        String qry3 = "select * from kategorie";
        katList = database.getKategorie(qry3);
        ArrayList<String> stringListKat = new ArrayList<>();
        ArrayList<Integer> intListKat = new ArrayList<>();

        int size = katList.size();
        for (int i = 0; i < size; i++) {
            stringListKat.add(katList.get(i).getKategorieTitel());
            intListKat.add(katList.get(i).getKategorieID());
        }

        txtKategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produktAnzahl = 1;
                etProduktAnzahl.setText(String.valueOf(1));
                txtProdukt.setText("Zgjidhe Produktin");
                prodList.clear();
                dialog = new Dialog(BestellungsActivitys.this);
                dialog.setContentView(R.layout.spnr_kot);
                dialog.getWindow().setLayout(650, 800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                EditText editText = dialog.findViewById(R.id.edit_text_kat);
                ListView listView = dialog.findViewById(R.id.list_view_kat);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(BestellungsActivitys.this, R.layout.mytextview_item, stringListKat);

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
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long ids) {
                        stringListProd.clear();
                        katID = intListKat.get(position);
                        txtKategorie.setText(adapter.getItem(position));

                        String qry2 = "select * from produkt where p_kategorieID = " + katID;
                        prodList = database.getProdukt(qry2);
                        int sizen = prodList.size();

                        for (int i = 0; i < sizen; i++) {
                            stringListProd.add(prodList.get(i).getProduktName());
                            intListProd.add(prodList.get(i).getProduktID());
                        }


                        dialog.dismiss();
                    }
                });

            }
        });
        txtProdukt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produktAnzahl = 1;
                etProduktAnzahl.setText(String.valueOf(1));
                dialog = new Dialog(BestellungsActivitys.this);
                dialog.setContentView(R.layout.spnt_prod);
                dialog.getWindow().setLayout(650, 800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                EditText editText = dialog.findViewById(R.id.edit_text_prod);
                listView1 = dialog.findViewById(R.id.list_view_prod);
                listView1.setAdapter(null);
                adapter1 = new ArrayAdapter<>(BestellungsActivitys.this, R.layout.mytextview_item, stringListProd);

                listView1.setAdapter(adapter1);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter1.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long ids) {
                        prodID = intListProd.get(position);
                        b_produktID = prodList.get(position).getProduktID();
                        b_produktPreis = prodList.get(position).getProduktPreis();
                        produktName = prodList.get(position).getProduktName();

                        //sqlSelect(id);
                        txtProdukt.setText(adapter1.getItem(position));
                        dialog.dismiss();
                    }
                });
            }


        });


        /////////////////////////////////////////////////////////////7
    }

}