
package data_access;

import domain.Video;
import domain.Lukuvinkki;
import java.util.ArrayList;

public interface VideoDAO {
    ArrayList<Lukuvinkki> getAll();
    void save(Video video);
    void markAsLuettu(String id);
}
