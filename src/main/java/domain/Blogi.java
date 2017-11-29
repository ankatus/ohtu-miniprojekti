package domain;

import tools.TextTools;


public class Blogi implements Lukuvinkki{
    
    private int id;
    private String otsikko;
    private String kirjoittaja;
    private String url;

    public Blogi(String otsikko, String kirjoittaja, String url) {
        this.otsikko = otsikko;
        this.kirjoittaja = kirjoittaja;
        this.url = url;
    }
    
    public Blogi(int id, String otsikko, String kirjoittaja, String url) {
        this.id = id;
        this.otsikko = otsikko;
        this.kirjoittaja = kirjoittaja;
        this.url = url;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    public String getUrl() {
        return url;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setKirjoittaja(String kirjoittaja) {
        this.kirjoittaja = kirjoittaja;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    @Override
    public String toString() {
        int maxLength = 20;
        String columnRepresentation = TextTools.fit(kirjoittaja, 20) + " | ";
        columnRepresentation += TextTools.fit(otsikko, 20) + " | ";
        columnRepresentation += TextTools.fit(url, 20) + " | ";
        return columnRepresentation;
    }

    @Override
    public void setLuettu(boolean luettu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getID() {
        return "B"+this.id;
    }
    
    
}