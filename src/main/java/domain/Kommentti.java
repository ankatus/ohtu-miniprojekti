package domain;

public class Kommentti {

    public String kommentti;
    public String kommentoija;
    public String aika;

    public Kommentti(String kommentoija, String kommentti, String aika) {
        this.kommentoija = kommentoija;
        this.kommentti = kommentti;
        this.aika = aika;
    }
    
    public Kommentti(String kommentoija, String kommentti) {
        this.kommentoija = kommentoija;
        this.kommentti = kommentti;        
    }

    public String getKommentti() {
        return kommentti;
    }

    public void setKommentti(String kommentti) {
        this.kommentti = kommentti;
    }

    public String getKommentoija() {
        return kommentoija;
    }

    public void setKommentoija(String kommentoija) {
        this.kommentoija = kommentoija;
    }
    
    @Override
    public String toString() {
        return kommentoija + " (" + this.aika + "):\n" + kommentti;
    }
}
