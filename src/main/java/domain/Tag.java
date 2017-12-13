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
public class Tag {
    private int id;
    private String tag;
    private String vinkki_id;

    public Tag(String tag) {
        this.tag = tag;
    }

    public Tag(int id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public Tag(String tag, String vinkki_id) {
        this.tag = tag;
        this.vinkki_id = vinkki_id;
    }
    

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return this.tag;
    }
    
    
    
    
}
