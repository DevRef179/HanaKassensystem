package com.example.hanakassensystem.Klassen;

public class Bestellung {

    private int b_produktID;
    private int tischID;
    private String produktName;
    private double produktPreis;
    private int anzahlProdukt;
    private int mitarbeiterID;
    private boolean empty;

    public Bestellung(int b_produktID, int tischID, String produktName, double produktPreis, int anzahlProdukt, int mitarbeiterID, boolean empty) {
        this.b_produktID = b_produktID;
        this.tischID = tischID;
        this.produktName = produktName;
        this.produktPreis = produktPreis;
        this.anzahlProdukt = anzahlProdukt;
        this.mitarbeiterID = mitarbeiterID;
        this.empty = empty;
    }

    public Bestellung(boolean empty) {
        this.empty = empty;
    }

    public int getB_produktID() {
        return b_produktID;
    }

    public void setB_produktID(int b_produktID) {
        this.b_produktID = b_produktID;
    }

    public int getTischID() {
        return tischID;
    }

    public void setTischID(int tischID) {
        this.tischID = tischID;
    }

    public String getProduktName() {
        return produktName;
    }

    public void setProduktName(String produktName) {
        this.produktName = produktName;
    }

    public double getProduktPreis() {
        return produktPreis;
    }

    public void setProduktPreis(double produktPreis) {
        this.produktPreis = produktPreis;
    }

    public int getAnzahlProdukt() {
        return anzahlProdukt;
    }

    public void setAnzahlProdukt(int anzahlProdukt) {
        this.anzahlProdukt = anzahlProdukt;
    }

    public int getMitarbeiterID() {
        return mitarbeiterID;
    }

    public void setMitarbeiterID(int mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }
}
