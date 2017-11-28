package data_access;

import domain.Kommentti;

import java.util.ArrayList;

public interface KommenttiDAO {
    ArrayList<Kommentti> getAllForID(String id);
}
