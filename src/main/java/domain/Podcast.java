
package domain;

import tools.TextTools;

public class Podcast implements Lukuvinkki {
    
    private int id;
    private String otsikko;
    private String tekija;
    private String url;
    private boolean kuunneltu;
    
    public Podcast(String otsikko, String tekija, String url) {
        this.otsikko = otsikko;
        this.tekija = tekija;
        this.url = url;
    }
    
    public Podcast(int id, String otsikko, String tekija, String url, boolean luettu) {
        this.id = id;
        this.otsikko = otsikko;
        this.tekija = tekija;
        this.url = url;
        this.kuunneltu = luettu;
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
    
    public void setKuunneltu(boolean kuunneltu) {
        this.kuunneltu = kuunneltu;
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
        return kuunneltu;
    }    
    
    @Override
    public Type getType() {
        return Type.PODCAST;
    }
    
    @Override
    public String toString() {
        int maxLength = 20;
        String columnRepresentation = TextTools.fit("podcast", maxLength) + " | ";
        columnRepresentation += TextTools.fit(tekija, maxLength) + " | ";
        columnRepresentation += TextTools.fit(otsikko, maxLength) + " | ";        
        if (kuunneltu) {
            columnRepresentation += TextTools.fit("kyllä", maxLength);
        } else {
            columnRepresentation += TextTools.fit("ei", maxLength);
        }
        return columnRepresentation;
    }
    
    
}
