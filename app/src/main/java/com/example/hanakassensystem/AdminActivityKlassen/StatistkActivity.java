package com.example.hanakassensystem.AdminActivityKlassen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hanakassensystem.Klassen.SumTotal;
import com.example.hanakassensystem.LogInActivity;
import com.example.hanakassensystem.R;
import com.example.hanakassensystem.database;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class StatistkActivity extends AppCompatActivity {

    Button btnExitStatisitk;
    Button btn2Kategorie,btn2Personal,btn2Produkt;

    ArrayList<SumTotal> sumTotalBarARray;
    ArrayList barArrayLst;
    database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistk);
        btnExitStatisitk = findViewById(R.id.btnExitStatistik);
        btn2Kategorie = findViewById(R.id.btnStatistik2Kategorie);
        btn2Personal = findViewById(R.id.btnStatistik2Personal);
        btn2Produkt = findViewById(R.id.btnStatistik2Produkt);
        BarChart barChart = findViewById(R.id.fragment_verticalbarchart_chart);
        database = new database(this);
        sumTotalBarARray = database.getSum("Select * from sumtotal");
        getData();

        BarDataSet barDataSet = new BarDataSet(barArrayLst,"Verkäufe");
        barDataSet.setLabel("Euro");

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColor(Color.parseColor("#104E78"));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(true);

        btn2Kategorie.setOnClickListener(view -> {
            Intent intent = new Intent(StatistkActivity.this, KategoryActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        btn2Personal.setOnClickListener(view -> {
            Intent intent = new Intent(StatistkActivity.this, PersonalActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        btn2Produkt.setOnClickListener(view -> {
            Intent intent = new Intent(StatistkActivity.this, ProduktActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
        btnExitStatisitk.setOnClickListener(view -> {
            Intent intent = new Intent(StatistkActivity.this, LogInActivity.class);
            finish();
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        });
    }

    private void getData(){

        barArrayLst = new ArrayList();
        barArrayLst.add(new BarEntry(1f, (float) sumTotalBarARray.get(0).getS_summe()));
        barArrayLst.add(new BarEntry(2f,20));
        barArrayLst.add(new BarEntry(3f,30));
        barArrayLst.add(new BarEntry(4f,40));
        barArrayLst.add(new BarEntry(5f,50));
        barArrayLst.add(new BarEntry(6f,50));
        barArrayLst.add(new BarEntry(7f,350));
    }
}