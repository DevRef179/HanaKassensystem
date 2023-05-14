package com.example.hanakassensystem.Klassen;

public class SumTotal {
    private int s_sumtotalID;
    private int s_produktID;
    private int s_mitarbeiterID;
    private double s_summe;
    private double s_einnzelpreis;
    private int s_Anzahl;
    private String created_at;
    private boolean empty;

    public SumTotal(int s_sumtotalID, int s_produktID, int s_mitarbeiterID, double s_summe, double s_einnzelpreis, int s_Anzahl, String created_at, boolean empty) {
        this.s_sumtotalID = s_sumtotalID;
        this.s_produktID = s_produktID;
        this.s_mitarbeiterID = s_mitarbeiterID;
        this.s_summe = s_summe;
        this.s_einnzelpreis = s_einnzelpreis;
        this.s_Anzahl = s_Anzahl;
        this.created_at = created_at;
        this.empty = empty;
    }

    public SumTotal(boolean empty) {
        this.empty = empty;
    }

    public int getS_sumtotalID() {
        return s_sumtotalID;
    }

    public void setS_sumtotalID(int s_sumtotalID) {
        this.s_sumtotalID = s_sumtotalID;
    }

    public int getS_produktID() {
        return s_produktID;
    }

    public void setS_produktID(int s_produktID) {
        this.s_produktID = s_produktID;
    }

    public int getS_mitarbeiterID() {
        return s_mitarbeiterID;
    }

    public void setS_mitarbeiterID(int s_mitarbeiterID) {
        this.s_mitarbeiterID = s_mitarbeiterID;
    }

    public double getS_summe() {
        return s_summe;
    }

    public void setS_summe(double s_summe) {
        this.s_summe = s_summe;
    }

    public double getS_einnzelpreis() {
        return s_einnzelpreis;
    }

    public void setS_einnzelpreis(double s_einnzelpreis) {
        this.s_einnzelpreis = s_einnzelpreis;
    }

    public int getS_Anzahl() {
        return s_Anzahl;
    }

    public void setS_Anzahl(int s_Anzahl) {
        this.s_Anzahl = s_Anzahl;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
