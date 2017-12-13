/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

import domain.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Otto
 */
public class DbTagDAO implements TagDAO{
    private Database database;

    public DbTagDAO(Database database) {
        this.database = database;
    }
    
    

    @Override
    public void saveTag(String tag) {
        ArrayList values = new ArrayList();

        values.add(tag);

        database.executeQueryUpdate("INSERT INTO Tag "
                + "(tag)"
                + " VALUES (?)", values);
        database.closeConnection();
    }

    @Override
    public void save(String lukuvinkki_id, int tag) {
        ArrayList values = new ArrayList();

        values.add(lukuvinkki_id);
        values.add(tag);

        database.executeQueryUpdate("INSERT INTO Taglink "
                + "(lukuvinkki, tagid)"
                + " VALUES (?, ?)", values);
        database.closeConnection();
    }

    @Override
    public ArrayList<Tag> getAll() {
        ArrayList<Tag> tagit = new ArrayList();

        ResultSet rS = database.executeQuerySelect("SELECT * FROM Tag", new ArrayList());

        try {
            while (rS.next()) {
                Tag tag = new Tag(rS.getInt("id"), rS.getString("tag"));
                
                tagit.add(tag);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        try {
            if (rS != null) {
                rS.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        database.closeConnection();
        
        return tagit;
    }

    @Override
    public ArrayList<Tag> getAllForId(String id) {
        ArrayList values = new ArrayList();
        values.add(id);

        ResultSet rS = database.executeQuerySelect("SELECT * "
                + "FROM Taglink, Tag WHERE Taglink.tagid=Tag.id AND lukuvinkki=?", values);

        ArrayList<Tag> tagit = new ArrayList<>();
        try {
            while (rS.next()) {
                String tag = rS.getString("tag");
                int tagid = Integer.parseInt(rS.getString("id"));

                tagit.add(new Tag(tagid, tag));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        try {
            if (rS != null) {
                rS.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        database.closeConnection();
        
        return tagit;
    }
    
}
