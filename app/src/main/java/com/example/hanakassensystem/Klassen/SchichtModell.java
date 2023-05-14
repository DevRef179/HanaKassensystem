package com.example.hanakassensystem.Klassen;

public class SchichtModell {

    private int schichtID;
    private int  s_mitarbeiterID;
    private String schichtBeginn;
    private String schichtEnde;
   private final boolean empty;

    public SchichtModell(int schichtID, int s_mitarbeiterID, String schichtBeginn, String schichtEnde, boolean empty) {
        this.schichtID = schichtID;
        this.s_mitarbeiterID = s_mitarbeiterID;
        this.schichtBeginn = schichtBeginn;
        this.schichtEnde = schichtEnde;
        this.empty = empty;
    }

    public SchichtModell(boolean empty) {
        this.empty = empty;
    }

    public int getSchichtID() {
        return schichtID;
    }

    public void setSchichtID(int schichtID) {
        this.schichtID = schichtID;
    }

    public int getS_mitarbeiterID() {
        return s_mitarbeiterID;
    }

    public void setS_mitarbeiterID(int s_mitarbeiterID) {
        this.s_mitarbeiterID = s_mitarbeiterID;
    }

    public String getSchichtBeginn() {
        return schichtBeginn;
    }

    public void setSchichtBeginn(String schichtBeginn) {
        this.schichtBeginn = schichtBeginn;
    }

    public String getSchichtEnde() {
        return schichtEnde;
    }

    public void setSchichtEnde(String schichtEnde) {
        this.schichtEnde = schichtEnde;
    }
}
