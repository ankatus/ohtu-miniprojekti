package data_access;

import domain.Lukuvinkki;
import domain.StubKirja;

import java.util.ArrayList;

public class StubKirjaDAO implements DAO {

    @Override
    public ArrayList<Lukuvinkki> getAll() {
        ArrayList<Lukuvinkki> list = new ArrayList<>();
        list.add(new StubKirja());
        list.add(new StubKirja());
        list.add(new StubKirja());
        list.add(new StubKirja());
        list.add(new StubKirja());
        list.add(new StubKirja());
        return list;
    }

    

    
}
