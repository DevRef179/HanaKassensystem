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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hanakassensystem.Klassen.Kategorie;
import com.example.hanakassensystem.Klassen.Mitarbeiter;
import com.example.hanakassensystem.Klassen.Produkt;
import com.example.hanakassensystem.Klassen.Tisch;
import com.example.hanakassensystem.MainActivity;
import com.example.hanakassensystem.R;
import com.example.hanakassensystem.database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BestellungsActivitys extends AppCompatActivity {
    ArrayList<Kategorie> katList = new ArrayList<>();
    ArrayList<Produkt> prodList = new ArrayList<>();
    ArrayList<Tisch> tischList = new ArrayList<>();
    private ArrayList<Mitarbeiter> mitList = new ArrayList<>();


    ArrayAdapter<String> adapter1;

    // --Commented out by Inspection START (14.05.2023 23:37):
    private String produktName;
    //
//
    private int b_produktID;
    // --Commented out by Inspection STOP (14.05.2023 23:37)
    private String produktname;
    private double b_produktPreis;

    // --Commented out by Inspection START (14.05.2023 23:37):
    private Dialog dialog;
    // --Commented out by Inspection STOP (14.05.2023 23:37)
    Integer tischNr = null;
    int lvID;
    private database database;
    private Integer katID = null;
    private Integer prodID = null;


    private TextView txtKategorie;
    private TextView txtProdukt;
    private EditText etProduktAnzahl;
    private Integer mitarbeiterID = null;
    private int tischID;
    private int produktAnzahl = 1;
    ListView listView1;

    final ArrayList<String> stringListProd = new ArrayList<>();
    final ArrayList<Integer> intListProd = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestellungs_activitys);
        txtProdukt = findViewById(R.id.testViewProdukt);
        txtKategorie = findViewById(R.id.testViewKategorie);
        FloatingActionButton fabCountPos = findViewById(R.id.fabCountPos);
        FloatingActionButton fabBooking = findViewById(R.id.fabBooking);
        etProduktAnzahl = findViewById(R.id.etAnzahlProdukt);
        TextView txtTischID = findViewById(R.id.txtTavolinaNrBestellungsActivity);
        database = new database(this);


        Intent intent = getIntent();
        tischID = intent.getIntExtra("tisch", 0);
        String qry = "select * from Tisch where tischid =" + tischID;
        tischList = database.getTisch(qry);

        mitarbeiterID = intent.getIntExtra("mitarbeiterID", 0);
        tischNr = tischList.get(0).getTischNummer();
        txtTischID.setText(tischNr.toString());


        //produktName = prodList.get()
        fabCountPos.setOnClickListener(view -> {
            produktAnzahl++;
            etProduktAnzahl.setText(String.valueOf(produktAnzahl));
        });
        fabBooking.setOnLongClickListener(view -> {
            Intent intent1 = new Intent(BestellungsActivitys.this, MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent1.putExtra("mitarbeiterID", mitarbeiterID);
            startActivity(intent1);

            return true;
        });
        fabBooking.setOnClickListener(view -> {

            if (katID != null && prodID != null) {
                if (tischID == 0) {
                    tischID = 0;
                } else {
                    Toast.makeText(BestellungsActivitys.this, "Save !!" + prodID, Toast.LENGTH_SHORT).show();
                    database.insertBestellung(b_produktID, tischID, produktName, b_produktPreis, produktAnzahl, mitarbeiterID);
                    database.updateTischTisch(1, tischID);
                    produktAnzahl = 1;
                    etProduktAnzahl.setText(String.valueOf(1));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);

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

        txtKategorie.setOnClickListener(v -> {
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
            listView.setOnItemClickListener((parent, view, position, ids) -> {
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
            });

        });
        txtProdukt.setOnClickListener(v -> {
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
            listView1.setOnItemClickListener((parent, view, position, ids) -> {
                prodID = intListProd.get(position);
                b_produktID = prodList.get(position).getProduktID();
                b_produktPreis = prodList.get(position).getProduktPreis();
                produktName = prodList.get(position).getProduktName();

                //sqlSelect(id);
                txtProdukt.setText(adapter1.getItem(position));
                dialog.dismiss();
            });
        });


        /////////////////////////////////////////////////////////////7
    }

}