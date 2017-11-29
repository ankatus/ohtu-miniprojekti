package data_access;

import domain.Kommentti;

import java.util.ArrayList;

public class StubKommenttiDAO implements KommenttiDAO {
    @Override
    public void save(String lukuvinkki_id, Kommentti kommentti) {

    }

    @Override
    public ArrayList<Kommentti> getAllForID(String id) {
        ArrayList<Kommentti> asdf = new ArrayList<>();
        asdf.add(new Kommentti("testaaja1", "testitesti"));
        asdf.add(new Kommentti("testaaja2", "testitestitestitestitestitestitestitestitestitestitestitestitestitestitestitestitestitestitestitestitestitestitestitesti"));
        asdf.add(new Kommentti("testaaja3", "testitestitestitestitestitestitestitestitestitestitestitestitestitestitestitesti"));
        return asdf;
    }
}
