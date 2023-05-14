package com.example.hanakassensystem.Klassen;

public class Produkt {
    private int produktID;
    private String produktName;
    private double produktPreis;
    private int p_kategorieID;
    private boolean empty;

    public Produkt(int produktID, String produktName, double produktPreis, int p_kategorieID, boolean empty) {
        this.produktID = produktID;
        this.produktName = produktName;
        this.produktPreis = produktPreis;
        this.p_kategorieID = p_kategorieID;
        this.empty = empty;
    }

    public Produkt(boolean empty) {
        this.empty = empty;
    }

    public int getProduktID() {
        return produktID;
    }

    public void setProduktID(int produktID) {
        this.produktID = produktID;
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

    public int getP_kategorieID() {
        return p_kategorieID;
    }

    public void setP_kategorieID(int p_kategorieID) {
        this.p_kategorieID = p_kategorieID;
    }
}
