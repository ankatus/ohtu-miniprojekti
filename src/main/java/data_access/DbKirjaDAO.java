package data_access;

import domain.Kirja;
import domain.Lukuvinkki;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbKirjaDAO implements KirjaDAO {

    private Database database;

    public DbKirjaDAO(Database database) {
        this.database = database;
    }

    
    @Override
    public void save(Kirja kirja) {

        try {
            Connection connection = database.connect();
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Kirja "
                    + "(otsikko, kirjoittaja, isbn)"
                    + " VALUES (?, ?, ?)");
            stmt.setObject(1, kirja.getOtsikko());
            stmt.setObject(2, kirja.getKirjoittaja());
            stmt.setObject(3, kirja.getIsbn());
            stmt.executeUpdate();
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<Lukuvinkki> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
