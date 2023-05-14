package com.example.hanakassensystem.Adaptern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hanakassensystem.Klassen.Kategorie;
import com.example.hanakassensystem.R;

import java.util.ArrayList;

public class KategorieAdapter extends BaseAdapter {


    private final ArrayList<Kategorie> lstKategorie;
    private final Context context;

    public KategorieAdapter(ArrayList<Kategorie> lstTische, Context context) {
        this.lstKategorie = lstTische;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lstKategorie.size();
    }

    @Override
    public Object getItem(int i) {
        return lstKategorie.get(i);
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
            view = inflater.inflate(R.layout.kategoriecard, null, true);

            holder.txtKategorie = view.findViewById(R.id.txtKategoriEmrinFragment);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.txtKategorie.setText(String.valueOf(lstKategorie.get(i).getKategorieTitel()));
        return view;
    }
    private static class ViewHolder {
        protected TextView
                txtKategorie;
    }
}
