package com.example.hanakassensystem.AdminActivityKlassen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hanakassensystem.Adaptern.BestellungsAdapter;
import com.example.hanakassensystem.Klassen.Bestellung;
import com.example.hanakassensystem.Klassen.Tisch;
import com.example.hanakassensystem.MainActivity;
import com.example.hanakassensystem.R;
import com.example.hanakassensystem.database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {

    private ArrayList<Bestellung> lstBestellung = new ArrayList<>();
    private ListView lvPay;

    private double preis;
    private double summPreis;
    private int anzahlProdukte;

    private int produktID;
    private int tischID;
    private Integer mitarbeiterID = null;
    private database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        TextView txtPayTischID = findViewById(R.id.txtPagesaTischNr);
        TextView txtSumTotal = findViewById(R.id.txtPayTotal);
        lvPay = findViewById(R.id.lvPagesa);
        FloatingActionButton fabPay = findViewById(R.id.fabPay);
        database = new database(this);



        Intent intent = getIntent();
        tischID = intent.getIntExtra("tisch", 0);
        mitarbeiterID = intent.getIntExtra("mitarbeiterID", 0);
        String qry = "select * from Tisch where tischid =" + tischID;
        ArrayList<Tisch> tischList = database.getTisch(qry);
        int tischNr = tischList.get(0).getTischNummer();
        txtPayTischID.setText(String.valueOf(tischNr));

        refreshList();
        for (int i = 0; i< lstBestellung.size(); i++){
            preis = lstBestellung.get(i).getProduktPreis();
            anzahlProdukte = lstBestellung.get(i).getAnzahlProdukt();
            double summ = preis * anzahlProdukte;
            summPreis += summ;
        }
        txtSumTotal.setText(String.valueOf(summPreis));


        fabPay.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    return false;
                }
            });

        fabPay.setOnClickListener(view -> {
            database.updateTischTisch(0,tischID);
            Intent intent1 = new Intent(PayActivity.this, MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent1.putExtra("mitarbeiterID", mitarbeiterID);
            startActivity(intent1);
            arraList2SQL();
            database.deleteBestellung(tischID);

        });

        fabPay.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent1 = new Intent(PayActivity.this, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent1.putExtra("mitarbeiterID", mitarbeiterID);
                startActivity(intent1);
                return true;
            }
        });

    }
    public void refreshList() {
        String qry = "select  b_produktID, b_tischID, b_produktName, b_produktPreis,  sum(anzahl) , b_mitarbeiterID from Bestellung where b_tischID = "+tischID+ "   group by b_produktID";
        lstBestellung = database.getBestellung(qry);
        if (!lstBestellung.isEmpty()){
            BestellungsAdapter bestellungsAdapter = new BestellungsAdapter(lstBestellung, this);
            lvPay.setAdapter(bestellungsAdapter);
        }
    }

    public void arraList2SQL(){
        for (int i = 0;i<lstBestellung.size(); i++ ){
            String qry = "select  b_produktID, b_tischID, b_produktName, b_produktPreis,  sum(anzahl) , b_mitarbeiterID from Bestellung where b_tischID = "+tischID+ "   group by b_produktID";
            lstBestellung = database.getBestellung(qry);
            int produktIDs = lstBestellung.get(i).getB_produktID();
            int mitarbeiterID= lstBestellung.get(i).getMitarbeiterID();
            double sum = preis * anzahlProdukte;
            int  anzahlProdukte = lstBestellung.get(i).getAnzahlProdukt();
            double einzelpreis = lstBestellung.get(i).getProduktPreis();

            database.insertSumTotal(produktIDs,mitarbeiterID,sum,einzelpreis,anzahlProdukte);
        }
    }

}