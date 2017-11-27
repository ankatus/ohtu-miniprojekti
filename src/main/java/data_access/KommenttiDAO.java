package data_access;

import domain.Kommentti;
import domain.Lukuvinkki;
import java.sql.SQLException;


public interface KommenttiDAO {
    void save(int lukuvinkki_id, Kommentti kommentti);
    Kommentti getKommentti(int lukuvinkki_id) throws SQLException;
    
}
