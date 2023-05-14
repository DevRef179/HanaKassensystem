package com.example.hanakassensystem.Adaptern;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hanakassensystem.Klassen.Mitarbeiter;
import com.example.hanakassensystem.R;

import java.util.ArrayList;

public class MitarbeiterAdapter extends BaseAdapter {


    private ArrayList<Mitarbeiter> lstMitarbeiter = new ArrayList<>();
    private final Context context;

    public MitarbeiterAdapter(ArrayList<Mitarbeiter> lstMitarbeiter, Context context) {
        this.lstMitarbeiter = lstMitarbeiter;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lstMitarbeiter.size();
    }

    @Override
    public Object getItem(int i) {
        return lstMitarbeiter.get(i);
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
            view = inflater.inflate(R.layout.mitarbeitercard, null, true);

            holder.txtMitarbeiterName = view.findViewById(R.id.txtPuntoriEmrinFragment);
            holder.txtMitarbeiterPasswoert = view.findViewById(R.id.txtPuntoriPasswortFragment);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.txtMitarbeiterName.setText("emrin: " + lstMitarbeiter.get(i).getMitarbeiterName());
        holder.txtMitarbeiterPasswoert.setText("psswd: " + lstMitarbeiter.get(i).getMitarbeiterPasswort());

        return view;
    }
    private static class ViewHolder {
        protected TextView
                txtMitarbeiterName,
                 txtMitarbeiterPasswoert;
    }

}
