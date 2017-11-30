package data_access;

import domain.Kirja;
import domain.Lukuvinkki;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbKirjaDAO implements KirjaDAO {

    private final Database database;

    public DbKirjaDAO(Database database) {
        this.database = database;
    }

    @Override
    public void save(Kirja kirja) {
        ArrayList values = new ArrayList();

        values.add(kirja.getOtsikko());
        values.add(kirja.getKirjoittaja());
        values.add(kirja.getIsbn());
        
        database.executeQueryUpdate("INSERT INTO Kirja "
                + "(otsikko, kirjoittaja, isbn)"
                + " VALUES (?, ?, ?)", values);
        database.closeConnection();
    }

    @Override
    public ArrayList<Lukuvinkki> getAll() {
        ArrayList<Lukuvinkki> kirja_lukuvinkit = new ArrayList();

        ResultSet rS = database.executeQuerySelect("SELECT * FROM Kirja");

        try {
            while (rS.next()) {
                Lukuvinkki kirja = new Kirja(rS.getInt("id"), rS.getString("otsikko"), rS.getString("kirjoittaja"), rS.getString("isbn"), rS.getBoolean("luettu"));
                
                kirja_lukuvinkit.add(kirja);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        database.closeConnection();
        
        return kirja_lukuvinkit;
    }

    public void markAsLuettu(String id) {
        int idNumber = Integer.parseInt(id.substring(1, id.length()));
        ArrayList values = new ArrayList<>();
        values.add(idNumber);
        database.executeQueryUpdate("UPDATE kirja SET luettu=1 WHERE id=?", values);
        database.closeConnection();
    }

}
