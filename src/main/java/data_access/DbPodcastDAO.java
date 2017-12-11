package data_access;

import domain.Lukuvinkki;
import domain.Podcast;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbPodcastDAO implements PodcastDAO {

    private final Database database;

    public DbPodcastDAO(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Lukuvinkki> getAll() {
        ArrayList<Lukuvinkki> podcast_vinkit = new ArrayList();

        ResultSet rs = database.executeQuerySelect("SELECT * FROM Podcast", new ArrayList());

        try {
            while (rs.next()) {
                Lukuvinkki podcast = new Podcast(rs.getInt("id"), rs.getString("otsikko"), rs.getString("tekija"), rs.getString("url"), rs.getBoolean("kuunneltu"));

                podcast_vinkit.add(podcast);
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

        return podcast_vinkit;
    }

    @Override
    public void save(Podcast podcast) {
        ArrayList values = new ArrayList();

        values.add(podcast.getOtsikko());
        values.add(podcast.getTekija());
        values.add(podcast.getUrl());

        database.executeQueryUpdate("INSERT INTO Podcast "
                + "(otsikko, tekija, url) "
                + "VALUES (?, ?, ?)", values);
        database.closeConnection();
    }

    @Override
    public void markAsKuunneltu(String id) {
        int idNumber = Integer.parseInt(id.substring(1, id.length()));
        ArrayList values = new ArrayList<>();
        values.add(idNumber);

        database.executeQueryUpdate("UPDATE Podcast SET kuunneltu=1 WHERE id=?", values);
        database.closeConnection();
    }

}
