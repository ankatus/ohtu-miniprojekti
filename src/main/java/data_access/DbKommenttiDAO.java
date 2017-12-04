package data_access;

import domain.Kommentti;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbKommenttiDAO implements KommenttiDAO {

    private Database database;

    public DbKommenttiDAO(Database database) {
        this.database = database;
    }

    @Override
    public void save(String lukuvinkki_id, Kommentti kommentti) {
        ArrayList values = new ArrayList();

        values.add(lukuvinkki_id);
        values.add(kommentti.getKommentoija());
        values.add(kommentti.getKommentti());

        database.executeQueryUpdate("INSERT INTO Kommentti "
                + "(lukuvinkki, kommentoija, kommentti)"
                + " VALUES (?, ?, ?)", values);
        database.closeConnection();
    }

    @Override
    public ArrayList<Kommentti> getAllForID(String lukuvinkki_id) {
        ArrayList values = new ArrayList();
        values.add(lukuvinkki_id);

        ResultSet rS = database.executeQuerySelect("SELECT * "
                + "FROM Kommentti WHERE lukuvinkki=?", values);

        ArrayList<Kommentti> kommentit = new ArrayList<>();

        try {
            while (rS.next()) {
                String kommentoija = rS.getString("kommentoija");
                String teksti = rS.getString("kommentti");

                kommentit.add(new Kommentti(kommentoija, teksti));
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

        return kommentit;
    }

    private String parseIdToTable(String id) {
        char letter = id.toCharArray()[0];
        if (letter == 'K') {
            return "kirja";
        } else if (letter == 'B') {
            return "blogi";
        }
        return null;
    }

}
