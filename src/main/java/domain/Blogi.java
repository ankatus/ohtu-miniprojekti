/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Otto
 */
public class Blogi implements Lukuvinkki{
    
    private String otsikko;
    private String kirjoittaja;
    private String url;

    public Blogi(String otsikko, String kirjoittaja, String url) {
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

    @Override
    public String toString() {
        return "otsikko=" + otsikko + ", kirjoittaja=" + kirjoittaja + ", url=" + url;
    }
    
    
}
