package data_access;

import domain.Kirja;
import domain.Lukuvinkki;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class KirjaDAO implements DAO {

    private Database database;

    public KirjaDAO(Database database) {
        this.database = database;
    }

    
    public void save(Kirja kirja) throws SQLException {

        try {
            Connection connection = database.connect();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Book "
                    + "(otsikko, kirjoittaja, isbn)"
                    + " VALUES (?, ?, ?)");
            stmt.setObject(1, kirja.getOtsikko());
            stmt.setObject(2, kirja.getKirjoittaja());
            stmt.setObject(3, kirja.getIsbn());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<Lukuvinkki> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
