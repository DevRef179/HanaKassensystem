package com.example.hanakassensystem.Klassen;

public class Mitarbeiter {
    private int mitarbeiterID;
    private String mitarbeiterName;
    private String mitarbeiterPasswort;
    private boolean empty;



    public Mitarbeiter(int mitarbeiterID, String mitarbeiterName, String mitarbeiterPasswort, boolean empty) {
        this.mitarbeiterID = mitarbeiterID;
        this.mitarbeiterName = mitarbeiterName;
        this.mitarbeiterPasswort = mitarbeiterPasswort;
        this.empty = empty;
    }

    public Mitarbeiter(boolean empty) {
        this.empty = empty;
    }

    public int getMitarbeiterID() {
        return mitarbeiterID;
    }

    public void setMitarbeiterID(int mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }

    public String getMitarbeiterName() {
        return mitarbeiterName;
    }

    public void setMitarbeiterName(String mitarbeiterName) {
        this.mitarbeiterName = mitarbeiterName;
    }

    public String getMitarbeiterPasswort() {
        return mitarbeiterPasswort;
    }

    public void setMitarbeiterPasswort(String mitarbeiterPasswort) {
        this.mitarbeiterPasswort = mitarbeiterPasswort;
    }
}
