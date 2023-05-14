package com.example.hanakassensystem.AdminActivityKlassen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hanakassensystem.Adaptern.BestellungsAdapter;
import com.example.hanakassensystem.Adaptern.TischAdapter;
import com.example.hanakassensystem.Klassen.Bestellung;
import com.example.hanakassensystem.Klassen.Tisch;
import com.example.hanakassensystem.MainActivity;
import com.example.hanakassensystem.R;
import com.example.hanakassensystem.database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {

    private ArrayList<Bestellung> lstBestellung = new ArrayList<>();
    private TextView txtPayTischID, txtSumTotal;
    private ListView lvPay;

    private double preis;
    private double summPreis;
    private double summ;
    private int anzahlProdukte;

    private int produktID;
    private int tischNr;
    private int tischID;
    private Integer mitarbeiterID = null;
    private BestellungsAdapter bestellungsAdapter;
 private ArrayList<Tisch>tischList = new ArrayList<>();
    private database database;
    private FloatingActionButton fabPay;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        txtPayTischID = findViewById(R.id.txtPagesaTischNr);
        txtSumTotal = findViewById(R.id.txtPayTotal);
        lvPay = findViewById(R.id.lvPagesa);
        fabPay = findViewById(R.id.fabPay);
        database = new database(this);



        Intent intent = getIntent();
        tischID = intent.getIntExtra("tisch", 0);
        mitarbeiterID = intent.getIntExtra("mitarbeiterID", 0);
        String qry = "select * from Tisch where tischid =" + tischID;
        tischList = database.getTisch(qry);
        tischNr = tischList.get(0).getTischNummer();
        txtPayTischID.setText(String.valueOf(tischNr));
        refreshList();

        for (int i = 0; i< lstBestellung.size(); i++){
            preis = lstBestellung.get(i).getProduktPreis();
            anzahlProdukte = lstBestellung.get(i).getAnzahlProdukt();
            summ = preis * anzahlProdukte;
            summPreis += summ;
        }

        txtSumTotal.setText(String.valueOf(summPreis));



        fabPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.updateTischTisch(0,tischID);

                Intent intent = new Intent(PayActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("mitarbeiterID", mitarbeiterID);
                startActivity(intent);
                arraList2SQL();
                database.deleteBestellung(tischID);
            }
        });



    }
    public void refreshList() {
        String qry = "select  b_produktID, b_tischID, b_produktName, b_produktPreis,  sum(anzahl) , b_mitarbeiterID from Bestellung where b_tischID = "+tischID+ "   group by b_produktID";
        lstBestellung = database.getBestellung(qry);
        if (!lstBestellung.isEmpty()){
            bestellungsAdapter = new BestellungsAdapter(lstBestellung, this);
            lvPay.setAdapter((BestellungsAdapter) bestellungsAdapter);
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