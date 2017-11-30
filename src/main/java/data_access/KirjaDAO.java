
package data_access;

import domain.Kirja;
import domain.Lukuvinkki;
import java.util.ArrayList;


public interface KirjaDAO {
    
    ArrayList<Lukuvinkki> getAll();
    void save(Kirja kirja);
    void markAsLuettu(String id);
    
}
