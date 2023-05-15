package com.example.hanakassensystem.Adaptern;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hanakassensystem.Klassen.Tisch;
import com.example.hanakassensystem.R;
import com.example.hanakassensystem.database;

import java.util.ArrayList;

public class TischAdapter extends BaseAdapter {

    private final ArrayList<Tisch> lstTische;
    private ArrayList<Tisch> lstTische2;

 private final Context context;
private ConstraintLayout con;
   private int position;

    private database database;
    private int bool;
    ArrayList<Tisch>tisches;

    public TischAdapter(ArrayList<Tisch> lstTische, Context context) {
        this.lstTische = lstTische;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lstTische.size();
    }

    @Override
    public Object getItem(int i) {
        return lstTische.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lstTische.get(i).getTischID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        sql(i);
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            int tischBesetzt = tisches.get(i).getBoolTischBesetztAsInt();
            if (tischBesetzt == 0){
                view = inflater.inflate(R.layout.tavolincard2, null, true);
            }else {
                view = inflater.inflate(R.layout.tavolincard, null, true);
            }
            holder.txtTavolinNr = view.findViewById(R.id.txtTavolinaNr);
            holder.con = view.findViewById(R.id.conTavolin);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.txtTavolinNr.setText(String.valueOf(lstTische.get(i).getTischNummer()));
        return view;
    }

    private static class ViewHolder {
        protected ConstraintLayout con;
        protected TextView
                txtTavolinNr;
    }
    private void sql(int i){
        getItemId(i);
        database = new database(context.getApplicationContext());
        tisches = database.getTisch("Select * from tisch order by boolTischBesetztAsInt DESC");
    }
}


