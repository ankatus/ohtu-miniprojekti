package data_access;

import domain.Kommentti;
import domain.Lukuvinkki;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbKommenttiDAO implements KommenttiDAO {

    private Database database;

    public DbKommenttiDAO(Database database) {
        this.database = database;
    }

    @Override
    public void save(int lukuvinkki_id, Kommentti kommentti) {
        try {
            Connection connection = database.connect();
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Kommentti "
                    + "(lukuvinkki, kommentti)"
                    + " VALUES (?, ?, ?)");
            stmt.setObject(1, lukuvinkki_id);
            stmt.setObject(2, kommentti.getKommentti());
            stmt.executeUpdate();
            stmt.close();
            connection.commit();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Kommentti getKommentti(int lukuvinkki_id) throws SQLException {

        Connection connection = database.connect();
        PreparedStatement stmt = connection.prepareStatement("SELECT * "
                + "FROM Kommentti WHERE lukuvinkki= ?");
        stmt.setObject(1, lukuvinkki_id);
        ResultSet rS = stmt.executeQuery();

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        String teksti = rs.getString("kommentti");
        Kommentti kommentti = new Kommentti(teksti);

        rs.close();
        stmt.close();
        connection.close();

        return kommentti;
    }

}
