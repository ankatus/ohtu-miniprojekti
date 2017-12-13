
package domain;


import tools.TextTools;

public class Kirja implements Lukuvinkki {
    
    private int id;
    private String otsikko;
    private String kirjoittaja;
    private String isbn;
    private boolean luettu;

    public Kirja(String otsikko, String kirjoittaja, String isbn) {
        this.otsikko = otsikko;
        this.kirjoittaja = kirjoittaja;
        this.isbn = isbn;
    }
    
    public Kirja(int id, String otsikko, String kirjoittaja, String isbn, boolean luettu) {
        this.id = id;
        this.otsikko = otsikko;
        this.kirjoittaja = kirjoittaja;
        this.isbn = isbn;
        this.luettu = luettu;
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
    public String getID() {
        return "K"+this.id;
    }

    @Override
    public Type getType() {
        return Type.KIRJA;
    }

    @Override
    public String view() {
        String rowRepresentation = TextTools.fit("Otsikko:", 20) + otsikko + "\n";
        rowRepresentation += TextTools.fit("Kirjoittaja:", 20)+ kirjoittaja + "\n";
        rowRepresentation += TextTools.fit("isbn:", 20) + isbn + "\n";
        String kyllaEi;
        if (luettu) {
            kyllaEi = "kyllä";
        } else {
            kyllaEi = "ei";
        }
        rowRepresentation += TextTools.fit("luettu:", 20) + kyllaEi;
        return rowRepresentation;
    }

    @Override
    public String toString() {
        String columnRepresentation = TextTools.fit("kirja", 20) + " | ";
        columnRepresentation += TextTools.fit(kirjoittaja, 20) + " | ";
        columnRepresentation += TextTools.fit(otsikko, 20) + " | ";
        if (luettu) {
            columnRepresentation += TextTools.fit("kyllä", 20);
        } else {
            columnRepresentation += TextTools.fit("ei", 20);
        }
        return columnRepresentation;
    }

    @Override
    public boolean getLuettu() {
        return luettu;
    }
    
    
    
    
    
}
