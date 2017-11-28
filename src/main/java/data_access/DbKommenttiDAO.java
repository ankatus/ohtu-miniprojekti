package data_access;

import domain.Kommentti;
import domain.Lukuvinkki;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                    + "(lukuvinkki, kommentoija, kommentti)"
                    + " VALUES (?, ?, ?)");
            stmt.setObject(1, lukuvinkki_id);
            stmt.setObject(2, kommentti.getKommentoija());
            stmt.setObject(3, kommentti.getKommentti());
            stmt.executeUpdate();
            stmt.close();
            connection.commit();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<Kommentti> getAllForID(String lukuvinkki_id) throws SQLException {

        Connection connection = database.connect();
        PreparedStatement stmt = connection.prepareStatement("SELECT * "
                + "FROM Kommentti WHERE lukuvinkki= ?");
        stmt.setObject(1, lukuvinkki_id);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Kommentti> kommentit = new ArrayList<>();
        while (rs.next()) {
            String kommentoija = rs.getString("kommentoija");
            String teksti = rs.getString("kommentti");
            Kommentti kommentti = new Kommentti(kommentoija, teksti);
            kommentit.add(new Kommentti(kommentoija, teksti));
        }
        rs.close();
        stmt.close();
        connection.close();

        return kommentit;
    }

}
