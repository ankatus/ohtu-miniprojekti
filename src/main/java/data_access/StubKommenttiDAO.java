package data_access;

import domain.Kommentti;
import domain.Lukuvinkki;

public class StubKommenttiDAO implements KommenttiDAO {

    public void save(int lukuvinkki_id, Kommentti kommentti) {
    }

    @Override
    public Kommentti getKommentti(int lukuvinkki_id) {
        return new Kommentti("Mauris sed libero. Suspendisse facilisis nulla in"
                + " lacinia laoreet, lorem velit accumsan velit vel mattis libero"
                + "nisl et sem. Proin interdum maecenas massa turpis sagittis in,"
                + " interdum non lobortis vitae massa. Quisque purus lectus, posuere"
                + " eget imperdiet nec sodales id arcu. Vestibulum elit pede dictum eu,"
                + " viverra non tincidunt eu ligula.");
    }

}
