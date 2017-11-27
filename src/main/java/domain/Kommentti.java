package domain;

public class Kommentti {

    public String kommentti;

    public Kommentti(String kommentti) {
        this.kommentti = kommentti;
    }

    public String getKommentti() {
        return kommentti;
    }

    public void setKommentti(String kommentti) {
        this.kommentti = kommentti;
    }

    @Override
    public String toString() {
        return kommentti;
    }
    
    

}
