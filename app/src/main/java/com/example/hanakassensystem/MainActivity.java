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

    ListView listView;
    Button btnCount, btnSum, btnLogout, btnShite;
    TextView tvOutput;
    database database;
    ConstraintLayout con;
    private     int tischID;
    private Integer mitarbeiterID = null;
    private Integer schichtMitarbeiterID = null;
    private FloatingActionButton fbmain;
    private ListView lvTavolinat;
    private TischAdapter tischAdapter;
    private String mitarbeiterName;
    private ArrayList<Tisch> tischListArray = new ArrayList<>();
    private ArrayList<SchichtModell> lstSchichtmodell = new ArrayList<>();
    private ArrayList<Mitarbeiter>mitList = new ArrayList<>();
    public static final int FLAG_ACTIVITY_NO_ANIMATION = 0X00010000;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = findViewById(R.id.txtMitarbeiterMain);
        fbmain = findViewById(R.id.floatingActionButton);
        lvTavolinat = findViewById(R.id.lstShitjeTavolin);
        database = new database(this);


        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);


        mitarbeiterID = intent.getIntExtra("mitarbeiterID", 0);


        mitList = database.getMitarbeiter("select * from mitarbeiter where mitarbeiterID = "+mitarbeiterID);

        mitarbeiterName = mitList.get(0).getMitarbeiterName();
        tvOutput.setText(mitarbeiterName);

        refreshTischLst();



        fbmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BestellungsActivitys.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        fbmain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int id =0;
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                lstSchichtmodell = database.getSchicht("select * from schicht");
                for (int i = lstSchichtmodell.size(); i-- > lstSchichtmodell.size()-1; ) {
                   id = lstSchichtmodell.get(i).getSchichtID();
                    Toast.makeText(MainActivity.this, "ID "+id, Toast.LENGTH_SHORT).show();
                }
                database.updateSchicht(id);
                startActivity(intent);
                return true;
            }
        });
        lvTavolinat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, BestellungsActivitys.class);
                 tischID = tischListArray.get(i).getTischID();

                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("tisch", tischID);
                intent.putExtra("mitarbeiterID", mitarbeiterID);

                startActivity(intent);

                Toast.makeText(MainActivity.this, "Tisch "+ tischID, Toast.LENGTH_SHORT).show();
            }
        });
        lvTavolinat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                tischID = tischListArray.get(i).getTischID();

                Intent intent = new Intent(MainActivity.this, PayActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("tisch", tischID);
                intent.putExtra("mitarbeiterID", mitarbeiterID);
                startActivity(intent);


                return false;
            }
        });


        /*
        btnLogout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity.this);
                alertdialog.setTitle("Tisch Nr hinzufügen?");
                final EditText tischNr = new EditText(MainActivity.this);
                tischNr.setInputType(InputType.TYPE_CLASS_NUMBER);
                tischNr.setMaxLines(1);
                tischNr.setGravity(Gravity.CENTER);
                alertdialog.setView(tischNr);
                alertdialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(MainActivity.this, "Speichern Erfolgreich", Toast.LENGTH_SHORT).show();

                        database.insertTisch(Integer.valueOf(String.valueOf(tischNr.getText())));
                        Toast.makeText(MainActivity.this, "Speichern Erfolgreich", Toast.LENGTH_SHORT).show();
                        refreshTischLst();
                    }
                });
                alertdialog.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alert = alertdialog.create();
                alert.setCanceledOnTouchOutside(false);
                alert.show();
                return true;
            }
        });

         */
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
        tischAdapter = new TischAdapter(tischListArray, this);
        lvTavolinat.setAdapter(tischAdapter);
    }

}