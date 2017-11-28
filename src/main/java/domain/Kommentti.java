package domain;

public class Kommentti {

    public String kommentti;
    public String kommentoija;

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
        this.kommentoija = kommentoija;  }
    
     
    

    @Override
    public String toString() {
        return kommentoija + ":\n" + kommentti;
    }
    
    

}
