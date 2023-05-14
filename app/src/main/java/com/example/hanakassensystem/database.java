package com.example.hanakassensystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hanakassensystem.Klassen.Bestellung;
import com.example.hanakassensystem.Klassen.Kategorie;
import com.example.hanakassensystem.Klassen.Mitarbeiter;
import com.example.hanakassensystem.Klassen.Produkt;
import com.example.hanakassensystem.Klassen.SchichtModell;
import com.example.hanakassensystem.Klassen.SumTotal;
import com.example.hanakassensystem.Klassen.Tisch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class database extends SQLiteOpenHelper {

    private long result;
    private static final String DBname = "firstBased3";
    SQLiteDatabase db;
    ContentValues cv;
    Cursor cs;


    public database(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        intitSQlite(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int odlVersion, int newVersion) {

        //updateSql(db);
    }

    //////////////////////   AUSLESEN   ///////
    ///  Read Tisch
    public ArrayList<Tisch> getTisch(String qry) {
        ArrayList<Tisch> tisch = new ArrayList<>();
        db = this.getReadableDatabase();
        try (Cursor cs = db.rawQuery(qry, null)) {
            if (cs.getCount() > -1) {
                cs.moveToFirst();
                while (!cs.isAfterLast()) {
                    int id = cs.getInt(0);
                    int tischNR = cs.getInt(1);
                    int tischbool = cs.getInt(2);
                    tisch.add(new Tisch(id, tischNR, tischbool, true));
                    cs.moveToNext();
                }
                cs.close();
                return tisch;
            }
            cs.close();
            tisch.add(new Tisch(false));
            return tisch;
        }
    }

    ///  Read Mitarbeiter
    public ArrayList<Mitarbeiter> getMitarbeiter(String qry) {
        ArrayList<Mitarbeiter> mitarbeiter = new ArrayList<>();
        db = this.getReadableDatabase();
        try (Cursor cs = db.rawQuery(qry, null)) {
            if (cs.getCount() > -1) {
                cs.moveToFirst();
                while (!cs.isAfterLast()) {
                    int mitarbeiterID = cs.getInt(0);
                    String mitarbeiterName = cs.getString(1);
                    String mitarbeiterPasswort = cs.getString(2);
                    mitarbeiter.add(new Mitarbeiter(mitarbeiterID, mitarbeiterName, mitarbeiterPasswort, true));
                    cs.moveToNext();
                }
                cs.close();
                return mitarbeiter;
            }
            cs.close();
            mitarbeiter.add(new Mitarbeiter(false));
            return mitarbeiter;
        }
    }

    // Read Kategorie
    public ArrayList<Kategorie> getKategorie(String qry) {
        ArrayList<Kategorie> kategorie = new ArrayList<>();
        db = this.getReadableDatabase();
        try (Cursor cs = db.rawQuery(qry, null)) {
            if (cs.getCount() > -1) {
                cs.moveToFirst();
                while (!cs.isAfterLast()) {
                    int kategorieID = cs.getInt(0);
                    String kategorieTitel = cs.getString(1);
                    kategorie.add(new Kategorie(kategorieID, kategorieTitel, true));
                    cs.moveToNext();
                }
                cs.close();
                return kategorie;
            }
            cs.close();
            kategorie.add(new Kategorie(false));
            return kategorie;
        }
    }
    // Read Produkt

    public ArrayList<SumTotal> getSum(String qry) {
        ArrayList<SumTotal> sumTotals = new ArrayList<>();
        db = this.getReadableDatabase();
        try (Cursor cs = db.rawQuery(qry, null)) {
            if (cs.getCount() > -1) {
                cs.moveToFirst();
                while (!cs.isAfterLast()) {
                    int s_sumtotalID = cs.getInt(0);
                    int s_produktID = cs.getInt(1);
                    int mitarbeiterID = cs.getInt(2);
                    double summe = cs.getDouble(3);
                    double einzelpreis = cs.getDouble(4);
                    int s_Anzahl = cs.getInt(5);
                    String created_at = cs.getString(6);
                    sumTotals.add(new SumTotal(s_sumtotalID, s_produktID, mitarbeiterID, summe, einzelpreis, s_Anzahl, created_at, true));
                    cs.moveToNext();
                }
                cs.close();
                return sumTotals;
            }
            cs.close();
            sumTotals.add(new SumTotal(false));
            return sumTotals;
        }
    }

    public ArrayList<Bestellung> getBestellung(String qry) {
        ArrayList<Bestellung> bestellungs = new ArrayList<>();
        db = this.getReadableDatabase();
        try (Cursor cs = db.rawQuery(qry, null)) {
            if (cs.getCount() > -1) {
                cs.moveToFirst();
                while (!cs.isAfterLast()) {
                    int produktID = cs.getInt(0);
                    int tischID = cs.getInt(1);
                    String produktName = cs.getString(2);
                    double produktPreis = cs.getDouble(3);
                    int anzahl = cs.getInt(4);
                    int mitarbeiterID = cs.getInt(5);
                    bestellungs.add(new Bestellung(produktID, tischID, produktName, produktPreis, anzahl, mitarbeiterID, true));
                    cs.moveToNext();
                }
                cs.close();
                return bestellungs;
            }
            cs.close();
            bestellungs.add(new Bestellung(false));
            return bestellungs;
        }
    }

    public ArrayList<SchichtModell> getSchicht(String qry) {
        ArrayList<SchichtModell> schicht = new ArrayList<>();
        db = this.getReadableDatabase();
        try (Cursor cs = db.rawQuery(qry, null)) {
            if (cs.getCount() > -1) {
                cs.moveToFirst();
                while (!cs.isAfterLast()) {
                    int schichtID = cs.getInt(0);
                    int mitarbeiterID = cs.getInt(0);
                    String schichtbeginn = cs.getString(2);
                    String schichtende = cs.getString(2);
                    schicht.add(new SchichtModell(schichtID, mitarbeiterID, schichtbeginn, schichtende, true));
                    cs.moveToNext();
                }
                cs.close();
                return schicht;
            }
            cs.close();
            schicht.add(new SchichtModell(false));
            return schicht;
        }
    }

    public ArrayList<Produkt> getProdukt(String qry) {
        ArrayList<Produkt> produkt = new ArrayList<>();
        db = this.getReadableDatabase();
        try (Cursor cs = db.rawQuery(qry, null)) {
            if (cs.getCount() > -1) {
                cs.moveToFirst();
                while (!cs.isAfterLast()) {
                    int produktID = cs.getInt(0);
                    String produktName = cs.getString(1);
                    double produktPreis = cs.getDouble(2);
                    int p_kategorieID = cs.getInt(3);
                    produkt.add(new Produkt(produktID, produktName, produktPreis, p_kategorieID, true));
                    cs.moveToNext();
                }
                cs.close();
                return produkt;
            }
            cs.close();
            produkt.add(new Produkt(false));
            return produkt;
        }
    }

    //////////////  INPUT //////////////
    // Insert Tisch
    public void insertTisch(int tischNummer) {
        db = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put("tischNr", tischNummer);
        result = db.insert("tisch", null, cv);
    }

    final String empty = "empty";

    public void insertArbeiterSchicht(int arbeiterID) {
        db = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put("s_mitarbeiterID", arbeiterID);
        cv.put("schichtbeginn", getDateTime());
        cv.put("schichtende", empty);
        result = db.insert("schicht", null, cv);
    }

    public void insertMitarbeiter(String mitarbeiterName, String mitarbeiterPasswort) {
        db = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put("mitarbeiterName", mitarbeiterName);
        cv.put("mitarbeiterPasswort", mitarbeiterPasswort);
        result = db.insert("mitarbeiter", null, cv);
    }

    // Insert Kategorie
    public void insertKategorie(String kategorieTitel) {
        db = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put("kategorieTitel", kategorieTitel);
        result = db.insert("kategorie", null, cv);
    }

    public void insertProdukt(String produktName, double produktPreis, int kategorieID) {
        db = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put("produktName", produktName);
        cv.put("produktPreis", produktPreis);
        cv.put("p_kategorieID", kategorieID);
        result = db.insert("produkt", null, cv);
    }

    // Insert Bestellung
    public void insertBestellung(int produktID, int tischID, String produktName, double produktPreis, int Anzahl, int mitarbeiterID) {
        db = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put("b_produktID", produktID);
        cv.put("b_tischID", tischID);
        cv.put("b_produktName", produktName);
        cv.put("b_produktPreis", produktPreis);
        cv.put("anzahl", Anzahl);
        cv.put("b_mitarbeiterID", mitarbeiterID);
        result = db.insert("bestellung", null, cv);

    }

    // Insert TotalSum
    public void insertSumTotal(int produktID, int s_mitarbeiterID, double summe, double einzelPreis, int anzahl) {
        db = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put("s_produktID", produktID);
        cv.put("s_mitarbeiterID", s_mitarbeiterID);
        cv.put("summe", summe);
        cv.put("einzelpreis", einzelPreis);
        cv.put("anzahl", anzahl);
        cv.put("created_at", getDateTime());
        result = db.insert("sumtotal", null, cv);
    }


    //////////// LÖSCHEN /////////////////////
    // Löschen Tisch
    public void deleteTisch(int tischID) {
        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        //deleting from users table
        db.delete("tisch", "tischID" + " = ?", new String[]{String.valueOf(tischID)});
    }

    // Löschen Bestellung
    public void deleteBestellung(int tischID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("bestellung", "b_tischID" + " = ?", new String[]{String.valueOf(tischID)});
    }

    // Löschen Kategorie
    public void deleteKategorie(int kategorieID) {
        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        //deleting from users table
        db.delete("kategorie", "kategorieID" + " = ?", new String[]{String.valueOf(kategorieID)});
    }

    // Löschen mitarbeiter
    public void deleteMitarbeiter(int mitarbeiterID) {
        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        //deleting from users table
        db.delete("mitarbeiter", "mitarbeiterID" + " = ?", new String[]{String.valueOf(mitarbeiterID)});
    }

    // Löschen produkt
    public void deleteProdukt(int produktID) {
        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        //deleting from users table
        db.delete("produkt", "produktID" + " = ?", new String[]{String.valueOf(produktID)});
    }


    ////////////////// UPDATE /////////////
    // Update Tisch
    public void updateTisch(int tischNR, int tischID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tischNr", tischNR);
        db.update("tisch", cv, "tischID = ?", new String[]{String.valueOf(tischID)});
    }
    public void updateSchicht(int SchichtID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("schichtende", getDateTime());
        db.update("schicht", cv, "schichtID = ?", new String[]{String.valueOf(SchichtID)});
    }
    public void updateTischTisch(int boolTischBesetztAsInt, int tischID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("boolTischBesetztAsInt", boolTischBesetztAsInt);
        db.update("tisch", cv, "tischID = ?", new String[]{String.valueOf(tischID)});
    }
    // Update Mitarbeiter
    public void updateMitarbeiter(String mitarbeiterName, String mitarbeiterPasswort, int mitarbeiterID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("mitarbeiterName", mitarbeiterName);
        cv.put("mitarbeiterPasswort", mitarbeiterPasswort);
        db.update("mitarbeiter", cv, "mitarbeiterID = ?", new String[]{String.valueOf(mitarbeiterID)});
    }
    // Update Kategorie
    public void updateKategorie(String kategorieTitel, int kategorieID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("kategorieTitel", kategorieTitel);
        db.update("kategorie", cv, "kategorieID = ?", new String[]{String.valueOf(kategorieID)});
    }
    // Update Produkt
    public void updateProdukt(String produktName, double produktPreis, int produktID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("produktName", produktName);
        cv.put("produktPreis", produktPreis);
        db.update("produkt", cv, "produktID = ?", new String[]{String.valueOf(produktID)});
    }
    ///////////// Datenbank Tabellen Initialisierung //////////
    private void intitSQlite(SQLiteDatabase db) {
        // Tisch
        db.execSQL("Drop table if exists tisch");
        db.execSQL("Create table tisch(tischID Integer Primary Key Autoincrement," +
                "tischNr Integer, " +
                "boolTischBesetztAsInt integer default 0 )");

        // Tisch
        db.execSQL("Drop table if exists schicht");
        db.execSQL("Create table schicht(schichtID Integer Primary Key Autoincrement," +
                "s_mitarbeiterID Integer," +
                "schichtbeginn DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "schichtende DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "Foreign key (s_mitarbeiterID)References mitarbeiter(mitarbeiterID))");

        // Kategorie
        db.execSQL("Drop table if exists kategorie");
        db.execSQL("Create table kategorie(kategorieID Integer Primary Key Autoincrement," +
                "kategorieTitel text unique)");

        // Produkt
        db.execSQL("Drop table if exists produkt");
        db.execSQL("Create table produkt(produktID Integer Primary Key Autoincrement," +
                "produktName Text," +
                "produktPreis Real," +
                "p_kategorieID Integer," +
                "Foreign key (p_kategorieID)References kategorie(kategorieID))");

        // Bestellung
        db.execSQL("Drop table if exists bestellung");
        db.execSQL("Create table bestellung(" +
                "b_produktID Integer, " +
                "b_tischID Integer ," +
                "b_produktName Text ," +
                "b_produktPreis Real ," +
                "anzahl Integer," +
                "b_mitarbeiterID Integer," +
                "Foreign key (b_produktID)References produkt(produktID))");

        //Mitarbeiter
        db.execSQL("Drop table if exists mitarbeiter");
        db.execSQL("Create table mitarbeiter(mitarbeiterID Integer Primary Key Autoincrement," +
                "mitarbeiterName Text," +
                "mitarbeiterPasswort Text)");

        //Statistik Summe Speicherung
        db.execSQL("Drop table if exists sumtotal");
        db.execSQL("Create table sumtotal(sumtotalID Integer Primary Key Autoincrement," +
                "s_produktID Integer ," +
                "s_mitarbeiterID Integer ," +
                "summe Real ," +
                "einzelpreis Real ," +
                "anzahl Integer ," +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "Foreign key (s_mitarbeiterID)References mitarbeiter(mitarbeiterID))");


    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd.MM yyyy HH:mm", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


}
