
package data_access;

import domain.Video;
import domain.Lukuvinkki;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbVideoDAO implements VideoDAO {
    
    private final Database database;
    
    public DbVideoDAO(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Lukuvinkki> getAll() {
        ArrayList<Lukuvinkki> video_lukuvinkit = new ArrayList();
        
        ResultSet rs = database.executeQuerySelect("SELECT * FROM Video", new ArrayList());
        
        try {
            while(rs.next()) {
                Lukuvinkki video = new Video(rs.getInt("id"), rs.getString("otsikko"), rs.getString("tekija"), rs.getString("url"), rs.getBoolean("katsottu"));
                
                video_lukuvinkit.add(video);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        database.closeConnection();
        
        return video_lukuvinkit;
    }    
        
    @Override
    public void save(Video video) {
        ArrayList values = new ArrayList();
        
        values.add(video.getOtsikko());
        values.add(video.getTekija());
        values.add(video.getUrl());
        
        database.executeQueryUpdate("INSERT INTO Video "
                + "(otsikko, tekija, url) "
                + "VALUES (?, ?, ?)", values);
        database.closeConnection();
    }    

    @Override
    public void markAsLuettu(String id) {
        int idNumber = Integer.parseInt(id.substring(1, id.length()));
        ArrayList values = new ArrayList<>();
        values.add(idNumber);
        
        database.executeQueryUpdate("UPDATE Video SET katsottu=1 WHERE id=?", values);
        database.closeConnection();
    }    
    
}
