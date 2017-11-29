
package domain;


import tools.TextTools;

public class Kirja implements Lukuvinkki {
    
    private int id;
    private String otsikko;
    private String kirjoittaja;
    private String isbn;

    public Kirja(String otsikko, String kirjoittaja, String isbn) {
        this.otsikko = otsikko;
        this.kirjoittaja = kirjoittaja;
        this.isbn = isbn;
    }
    
    public Kirja(int id, String otsikko, String kirjoittaja, String isbn) {
        this.id = id;
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

    public String getIsbn() {
        return isbn;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setKirjoittaja(String kirjoittaja) {
        this.kirjoittaja = kirjoittaja;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setLuettu(boolean luettu) {

    }

    @Override
    public String getID() {
        return "K"+this.id;
    }

    @Override
    public String toString() {
        int maxLength = 20;
        String columnRepresentation = TextTools.fit(kirjoittaja, 20) + " | ";
        columnRepresentation += TextTools.fit(otsikko, 20) + " | ";
        columnRepresentation += TextTools.fit(isbn, 20) + " | ";
        return columnRepresentation;
    }
    
    
    
    
    
}
