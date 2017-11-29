package data_access;

import domain.Blogi;
import domain.Lukuvinkki;
import java.util.ArrayList;


public interface BlogiDAO {
    ArrayList<Lukuvinkki> getAll();
    void save(Blogi blogi);
}