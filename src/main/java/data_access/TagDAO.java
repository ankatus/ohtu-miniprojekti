/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

import domain.Tag;
import java.util.ArrayList;

/**
 *
 * @author Otto
 */
public interface TagDAO {
    void saveTag(String tag);
    void save (String lukuvinkki_id, int tag);
    ArrayList<Tag> getAll();
    ArrayList<Tag> getAllForId(String id);
}
