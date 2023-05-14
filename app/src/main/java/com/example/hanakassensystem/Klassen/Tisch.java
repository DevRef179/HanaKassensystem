package com.example.hanakassensystem.Klassen;

public class Tisch {

    private int tischID;
    private int tischNummer;
    private int boolTischBesetztAsInt;
    private final boolean empty;

    public int getTischID() {
        return tischID;
    }

    public void setTischID(int tischID) {
        this.tischID = tischID;
    }

    public int getTischNummer() {
        return tischNummer;
    }

    public void setTischNummer(int tischNummer) {
        this.tischNummer = tischNummer;
    }

    public int getBoolTischBesetztAsInt() {
        return boolTischBesetztAsInt;
    }

    public void setBoolTischBesetztAsInt(int boolTischBesetztAsInt) {
        this.boolTischBesetztAsInt = boolTischBesetztAsInt;
    }

    public Tisch(int tischID, int tischNummer, int boolTischBesetztAsInt, boolean empty) {
        this.tischID = tischID;
        this.tischNummer = tischNummer;
        this.boolTischBesetztAsInt = boolTischBesetztAsInt;
        this.empty = empty;
    }

    public Tisch(boolean empty) {
        this.empty = empty;
    }

}
