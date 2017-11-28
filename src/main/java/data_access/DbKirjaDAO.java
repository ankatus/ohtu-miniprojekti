package data_access;

import domain.Kirja;
import domain.Lukuvinkki;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        database.executeQueryInsert("INSERT INTO Kirja "
                + "(otsikko, kirjoittaja, isbn)"
                + " VALUES (?, ?, ?)", values);
    }

    @Override
    public ArrayList<Lukuvinkki> getAll() {
        ArrayList<Lukuvinkki> kirja_lukuvinkit = new ArrayList();

        ResultSet rS = database.executeQuerySelect("SELECT * FROM Kirja");

        try {
            while (rS.next()) {
                Lukuvinkki kirja = new Kirja(rS.getString("otsikko"), rS.getString("kirjoittaja"), rS.getString("isbn"));
                
                kirja_lukuvinkit.add(kirja);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return kirja_lukuvinkit;
    }

}
