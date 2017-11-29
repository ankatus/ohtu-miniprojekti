package data_access;

import domain.Kommentti;

import java.sql.SQLException;
import java.util.ArrayList;

public interface KommenttiDAO {
    void save(int lukuvinkki_id, Kommentti kommentti);
    ArrayList<Kommentti> getAllForID(String lukuvinkki_id) throws SQLException;