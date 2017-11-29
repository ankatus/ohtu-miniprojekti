package data_access;

import domain.Blogi;
import domain.Lukuvinkki;
import domain.StubBlogi;

import java.util.ArrayList;

public class StubBlogiDAO implements BlogiDAO {
    @Override
    public ArrayList<Lukuvinkki> getAll() {
        ArrayList<Lukuvinkki> asdf = new ArrayList<>();
        asdf.add(new StubBlogi());
        asdf.add(new StubBlogi());
        asdf.add(new StubBlogi());
        asdf.add(new StubBlogi());
        asdf.add(new StubBlogi());
        return asdf;
    }

    @Override
    public void save(Blogi blogi) {
        //not yet supported
    }

}
