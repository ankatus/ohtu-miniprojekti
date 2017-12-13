
package data_access;

import domain.Lukuvinkki;
import domain.Podcast;
import java.util.ArrayList;

public interface PodcastDAO {
    ArrayList<Lukuvinkki> getAll();
    void save(Podcast podcast);
    void markAsKuunneltu(String id);
}
