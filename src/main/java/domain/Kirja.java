
package domain;


public class Kirja implements Lukuvinkki {
    
    private String otsikko;
    private String kirjoittaja;
    private int isbn;

    public Kirja(String otsikko, String kirjoittaja, int isbn) {
        this.otsikko = otsikko;
        this.kirjoittaja = kirjoittaja;
        this.isbn = isbn;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setKirjoittaja(String kirjoittaja) {
        this.kirjoittaja = kirjoittaja;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return kirjoittaja + ": " + otsikko + " (" + isbn + ')';
    }
    
    
    
    
    
}
