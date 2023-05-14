package com.example.hanakassensystem.Klassen;

public class Kategorie {
    private  int kategorieID;
    private String kategorieTitel;
    private boolean empty;

    public Kategorie(int kategorieID, String kategorieTitel, boolean empty) {
        this.kategorieID = kategorieID;
        this.kategorieTitel = kategorieTitel;
        this.empty = empty;
    }

    public Kategorie(boolean empty) {
        this.empty = empty;
    }

    public int getKategorieID() {
        return kategorieID;
    }

    public void setKategorieID(int kategorieID) {
        this.kategorieID = kategorieID;
    }

    public String getKategorieTitel() {
        return kategorieTitel;
    }

    public void setKategorieTitel(String kategorieTitel) {
        this.kategorieTitel = kategorieTitel;
    }
}
