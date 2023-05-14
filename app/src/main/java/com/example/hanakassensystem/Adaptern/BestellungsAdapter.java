package com.example.hanakassensystem.Adaptern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hanakassensystem.Klassen.Bestellung;
import com.example.hanakassensystem.R;

import java.util.ArrayList;

public class BestellungsAdapter extends BaseAdapter {

    private ArrayList<Bestellung> lstBestellung;
    private Context context;

    public BestellungsAdapter(ArrayList<Bestellung> lstBestellung, Context context) {
        this.lstBestellung = lstBestellung;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lstBestellung.size();
    }

    @Override
    public Object getItem(int i) {
        return lstBestellung.get(i);
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
            view = inflater.inflate(R.layout.totalbestellungscard, null, true);

            holder.txtProduktAnzahl = (TextView) view.findViewById(R.id.txtBestellungProduktAnzahl2);
            holder.txtProduktName = (TextView) view.findViewById(R.id.txtBestellungProduktName2);
            holder.txtEinzelPreis = (TextView) view.findViewById(R.id.txtBestellungProduktEuro);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txtProduktAnzahl.setText(String.valueOf(lstBestellung.get(i).getAnzahlProdukt()));
        holder.txtEinzelPreis.setText(String.valueOf(lstBestellung.get(i).getProduktPreis()));
        holder.txtProduktName.setText(String.valueOf(lstBestellung.get(i).getProduktName()));
        return view;
    }
    private class ViewHolder {
        protected TextView
                txtProduktAnzahl,
                txtProduktName,
                txtEinzelPreis;
    }
}
