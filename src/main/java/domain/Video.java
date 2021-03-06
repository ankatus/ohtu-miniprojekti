
package domain;

import tools.TextTools;

public class Video implements Lukuvinkki {
    
    private int id;
    private String otsikko;
    private String tekija;
    private String url;
    private boolean luettu;
    
    public Video(String otsikko, String tekija, String url) {
        this.otsikko = otsikko;
        this.tekija = tekija;
        this.url = url;
    }
    
    public Video(int id, String otsikko, String tekija, String url, boolean luettu) {
        this.id = id;
        this.otsikko = otsikko;
        this.tekija = tekija;
        this.url = url;
        this.luettu = luettu;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }
    
    public void setTekija(String tekija) {
        this.tekija = tekija;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setLuettu(boolean luettu) {
        this.luettu = luettu;
    }

    @Override
    public String getID() {
        return "V" + this.id;
    }
    
    public String getOtsikko() {
        return this.otsikko;
    }
    
    public String getTekija() {
        return this.tekija;
    }
    
    public String getUrl() {
        return this.url;
    }

    @Override
    public boolean getLuettu() {
        return luettu;
    }    
    
    @Override
    public Type getType() {
        return Type.VIDEO;
    }

    @Override
    public String view() {
        String rowRepresentation = TextTools.fit("Otsikko:", 20) + otsikko + "\n";
        rowRepresentation += TextTools.fit("Tekijä:", 20) + tekija + "\n";
        rowRepresentation += TextTools.fit("url:", 20) + url + "\n";
        String kyllaEi;
        if (luettu) {
            kyllaEi = "kyllä";
        } else {
            kyllaEi = "ei";
        }
        rowRepresentation += TextTools.fit("katsottu:", 20) + kyllaEi;
        return rowRepresentation;
    }
    
    @Override
    public String toString() {
        String columnRepresentation = TextTools.fit("video", 20) + " | ";
        columnRepresentation += TextTools.fit(tekija, 20) + " | ";
        columnRepresentation += TextTools.fit(otsikko, 20) + " | ";
        if (luettu) {
            columnRepresentation += TextTools.fit("kyllä", 20);
        } else {
            columnRepresentation += TextTools.fit("ei", 20);
        }
        return columnRepresentation;
    }
    
}
