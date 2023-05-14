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

    private ArrayList<Tisch> lstTische;
    private ArrayList<Tisch> lstTische2;
    private Context context;
    private ConstraintLayout con;

    private int position;
    private database database;
    private int bool;

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
        bool = lstTische2.get(i).getBoolTischBesetztAsInt();
        return lstTische.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.tavolincard, null, true);

            holder.txtTavolinNr = (TextView) view.findViewById(R.id.txtTavolinaNr);
            holder.con = (ConstraintLayout) view.findViewById(R.id.conTavolin);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.txtTavolinNr.setText(String.valueOf(lstTische.get(i).getTischNummer()));
        if (bool == 1) {
          holder.con.setBackgroundColor(Color.parseColor("#5169FF"));
        }
        return view;
    }

    private class ViewHolder {
        protected ConstraintLayout con;
        protected TextView
                txtTavolinNr;
    }
}


