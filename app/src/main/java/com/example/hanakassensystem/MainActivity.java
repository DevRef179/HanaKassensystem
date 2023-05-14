package com.example.hanakassensystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hanakassensystem.Adaptern.TischAdapter;
import com.example.hanakassensystem.AdminActivityKlassen.BestellungsActivitys;
import com.example.hanakassensystem.AdminActivityKlassen.PayActivity;
import com.example.hanakassensystem.Klassen.Mitarbeiter;
import com.example.hanakassensystem.Klassen.SchichtModell;
import com.example.hanakassensystem.Klassen.Tisch;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnCount, btnSum, btnLogout, btnShite;
    TextView tvOutput;
    database database;
    ConstraintLayout con;
    private int tischID;
    private Integer mitarbeiterID = null;
    private final Integer schichtMitarbeiterID = null;

    private ListView lvTavolinat;
    private ArrayList<Tisch> tischListArray = new ArrayList<>();
    private ArrayList<SchichtModell> lstSchichtmodell = new ArrayList<>();
    public static final int FLAG_ACTIVITY_NO_ANIMATION = 0X00010000;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = findViewById(R.id.txtMitarbeiterMain);
        FloatingActionButton fbmain = findViewById(R.id.floatingActionButton);
        lvTavolinat = findViewById(R.id.lstShitjeTavolin);
        database = new database(this);


        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);


        mitarbeiterID = intent.getIntExtra("mitarbeiterID", 0);


        ArrayList<Mitarbeiter> mitList = database.getMitarbeiter("select * from mitarbeiter where mitarbeiterID = " + mitarbeiterID);

        String mitarbeiterName = mitList.get(0).getMitarbeiterName();
        tvOutput.setText(mitarbeiterName);

        refreshTischLst();


        fbmain.setOnClickListener(view -> {
            Intent intent14 = new Intent(MainActivity.this, BestellungsActivitys.class);
            intent14.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent14);
        });
        fbmain.setOnLongClickListener(view -> {
            int id = 0;
            Intent intent13 = new Intent(MainActivity.this, LogInActivity.class);
            intent13.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            lstSchichtmodell = database.getSchicht("select * from schicht");
            for (int i = lstSchichtmodell.size(); i-- > lstSchichtmodell.size() - 1; ) {
                id = lstSchichtmodell.get(i).getSchichtID();
                Toast.makeText(MainActivity.this, "ID " + id, Toast.LENGTH_SHORT).show();
            }
            database.updateSchicht(id);
            startActivity(intent13);
            return true;
        });
        lvTavolinat.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent12 = new Intent(MainActivity.this, BestellungsActivitys.class);
            tischID = tischListArray.get(i).getTischID();

            intent12.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent12.putExtra("tisch", tischID);
            intent12.putExtra("mitarbeiterID", mitarbeiterID);

            startActivity(intent12);

            Toast.makeText(MainActivity.this, "Tisch " + tischID, Toast.LENGTH_SHORT).show();
        });
        lvTavolinat.setOnItemLongClickListener((adapterView, view, i, l) -> {
            tischID = tischListArray.get(i).getTischID();

            Intent intent1 = new Intent(MainActivity.this, PayActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent1.putExtra("tisch", tischID);
            intent1.putExtra("mitarbeiterID", mitarbeiterID);
            startActivity(intent1);


            return false;
        });


        /*

         con = findViewById(R.id.conTavolin);

         con.setBackgroundColor(Color.CYAN);
         for (int i = 0; i<15; i++){
             int price = new Random().nextInt(100);
             //database.insertAbhandlung(String.valueOf(price));
         }

         listView.setAdapter(new ArrayAdapter<>(this,
                 android.R.layout.simple_list_item_1,database.getAbhandlung()));

         tvOutput.setText(String.format("Total Entry:  %s",database.getCount()3
         ));

          */
    }

    public void refreshTischLst() {
        String qry = "select * from Tisch";
        tischListArray = database.getTisch(qry);
        TischAdapter tischAdapter = new TischAdapter(tischListArray, this);
        lvTavolinat.setAdapter(tischAdapter);
    }

}