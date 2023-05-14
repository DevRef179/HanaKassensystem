package com.example.hanakassensystem.Adaptern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hanakassensystem.Klassen.Produkt;
import com.example.hanakassensystem.R;

import java.util.ArrayList;

public class ProduktAdapter extends BaseAdapter {


    private ArrayList<Produkt> lstProdukte;
    private Context context;

    public ProduktAdapter(ArrayList<Produkt> lstProdukte, Context context) {
        this.lstProdukte = lstProdukte;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lstProdukte.size();
    }

    @Override
    public Object getItem(int i) {
        return lstProdukte.get(i);
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
            view = inflater.inflate(R.layout.produktcard, null, true);

            holder.txtProduktName = (TextView) view.findViewById(R.id.txtProduktNameFragment);
            holder.txtProduktPreis = (TextView) view.findViewById(R.id.txtProduktAnzahlFragment);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.txtProduktName.setText(String.valueOf(lstProdukte.get(i).getProduktName()));
        holder.txtProduktPreis.setText(String.valueOf(lstProdukte.get(i).getProduktPreis()));
        return view;
    }
    private class ViewHolder {
        protected TextView
                txtProduktName,
                txtProduktPreis;
    }
}
