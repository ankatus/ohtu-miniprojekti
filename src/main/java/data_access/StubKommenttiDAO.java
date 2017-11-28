package data_access;

import domain.Kommentti;

import java.util.ArrayList;

public class StubKommenttiDAO implements KommenttiDAO {
    @Override
    public ArrayList<Kommentti> getAllForID(String id) {
        ArrayList<Kommentti> asdf = new ArrayList<>();
        return asdf;
    }
}
