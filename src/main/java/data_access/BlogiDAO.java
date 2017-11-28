package data_access;

import domain.Lukuvinkki;

import java.util.ArrayList;

public interface BlogiDAO {
    ArrayList<Lukuvinkki> getAll();
}
