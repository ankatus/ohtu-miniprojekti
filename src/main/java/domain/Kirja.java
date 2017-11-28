
package domain;


import tools.TextTools;

public class Kirja implements Lukuvinkki {
    
    private String otsikko;
    private String kirjoittaja;
    private String isbn;

    public Kirja(String otsikko, String kirjoittaja, String isbn) {
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

    @Override
    public String getID() {
        return null;
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
