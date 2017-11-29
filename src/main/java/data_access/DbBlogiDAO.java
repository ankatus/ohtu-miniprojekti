package data_access;

import domain.Blogi;
import domain.Lukuvinkki;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbBlogiDAO implements BlogiDAO {

    private Database database;

    public DbBlogiDAO(Database database) {
        this.database = database;
    }

    @Override
    public void save(Blogi blogi) {

        try {
            Connection connection = database.connect();
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Blogi "
                    + "(otsikko, kirjoittaja, url)"
                    + " VALUES (?, ?, ?)");
            stmt.setObject(1, blogi.getOtsikko());
            stmt.setObject(2, blogi.getKirjoittaja());
            stmt.setObject(3, blogi.getUrl());
            stmt.executeUpdate();
            stmt.close();
            connection.commit();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<Lukuvinkki> getAll() {
        ArrayList<Lukuvinkki> blogi_lukuvinkit = new ArrayList();

        try {
            Connection connection = database.connect();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Blogi");

            ResultSet rS = stmt.executeQuery();

            while (rS.next()) {
                Lukuvinkki blogi = new Blogi(rS.getInt("id"), rS.getString("otsikko"), rS.getString("kirjoittaja"), rS.getString("url"));

                blogi_lukuvinkit.add(blogi);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return blogi_lukuvinkit;
    }
}
